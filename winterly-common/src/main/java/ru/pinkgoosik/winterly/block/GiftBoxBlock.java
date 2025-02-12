package ru.pinkgoosik.winterly.block;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.locale.Language;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import ru.pinkgoosik.winterly.Winterly;
import ru.pinkgoosik.winterly.block.entity.GiftBoxBlockEntity;
import ru.pinkgoosik.winterly.block.entity.GiftBoxBlockEntityData;
import ru.pinkgoosik.winterly.data.GiftBoxData;

import java.util.List;

@SuppressWarnings("NullableProblems")
public class GiftBoxBlock extends Block implements EntityBlock {
	public static final VoxelShape SHAPE = box(4, 0, 4, 12, 8, 12);

	public GiftBoxBlock(Properties settings) {
		super(settings);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new GiftBoxBlockEntity(pos, state);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return GiftBoxBlock.SHAPE;
	}

	@Override
	public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		super.setPlacedBy(world, pos, state, placer, stack);

		if(world.getBlockEntity(pos) instanceof GiftBoxBlockEntityData entity && placer != null) {
			if(stack.getComponents().has(DataComponents.CUSTOM_DATA)) {
				entity.loadAdditional(stack.getComponents().get(DataComponents.CUSTOM_DATA).copyTag(), placer.registryAccess());
			}

		}
	}

	@Override
	public BlockState playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
		super.playerWillDestroy(world, pos, state, player);

		if(!world.isClientSide && world.getBlockEntity(pos) instanceof GiftBoxBlockEntityData entity) {
			ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);


			if(!EnchantmentHelper.hasTag(stack, TagKey.create(Registries.ENCHANTMENT, Winterly.id("gift_box_pickup")))) {
				if(entity.getStacks().isEmpty()) {
					popResource(world, pos, new ItemStack(this.asItem()));
				}
				else {
					entity.getStacks().forEach(st -> popResource(world, pos, st));
				}
			}
			else {
				ItemStack box = new ItemStack(this);

				if(!entity.getStacks().isEmpty()) {
					var nbt = new CompoundTag();
					entity.saveAdditional(nbt, player.registryAccess());
					box.applyComponents(DataComponentMap.builder().set(DataComponents.CUSTOM_DATA, CustomData.of(nbt)).build());
				}
				popResource(world, pos, box);
			}


		}
		return state;
	}

	@Override
	protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		if(!level.isClientSide && level.getBlockEntity(pos) instanceof GiftBoxBlockEntityData entity) {

			if(!stack.isEmpty() && entity.getStacks().size() < Winterly.config.getGiftBoxCapacity()) {
				if(stack.getItem() instanceof BlockItem blockItem) {
					if(blockItem.getBlock() instanceof ShulkerBoxBlock || blockItem.getBlock() instanceof GiftBoxBlock) {
						return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
					}
				}
				entity.getStacks().add(stack.copy());
				((BlockEntity)entity).setChanged();
				player.setItemInHand(hand, ItemStack.EMPTY);
				level.playSound(null, pos, SoundEvents.BUNDLE_INSERT, SoundSource.BLOCKS, 1, 1);
				return ItemInteractionResult.SUCCESS;
			}
		}
		return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
	}

	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		super.appendHoverText(stack, context, tooltip, tooltipFlag);


		if(stack.getComponents().has(DataComponents.CUSTOM_DATA)) {
			var tag = stack.getComponents().get(DataComponents.CUSTOM_DATA).copyTag();

			if(tag.contains("giftBoxData")) {
				var gift = GiftBoxData.fromNbt(tag.getCompound("giftBoxData"), context.registries());

				gift.stacks.forEach(st -> {
					String name = Language.getInstance().getOrDefault(st.getDescriptionId());
					tooltip.add(Component.nullToEmpty("- " + name + " x" + st.getCount()).toFlatList(Style.EMPTY.withColor(ChatFormatting.GRAY)).get(0));
				});
				return;
			}

		}

		Language lang = Language.getInstance();
		String key = "description.winterly.gift_box.";

		for(int i = 0; i <= 32; i++) {
			if(lang.has(key + i)) {
				tooltip.add(Component.translatable(key + i).toFlatList(Style.EMPTY.withColor(ChatFormatting.GRAY)).get(0));
			}
			if(!lang.has(key + (i + 1))) {
				break;
			}
		}

	}
}
