package ru.pinkgoosik.winterly.mixin.common;

import net.minecraft.world.item.DiggerItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(DiggerItem.class)
public abstract class MiningToolItemMixin {

//	@Shadow
//	@Final
//	private TagKey<Block> blocks;
//
//	@Shadow
//	@Final
//	protected float speed;
//
//	@Inject(method = "isCorrectToolForDrops", at = @At("HEAD"), cancellable = true)
//	void isSuitableFor(BlockState state, CallbackInfoReturnable<Boolean> cir) {
//		if(blocks.equals(BlockTags.MINEABLE_WITH_SHOVEL)) {
//			if(state.is(CommonWinterlyBlocks.FROZEN_FLOWER)) {
//				if(state.getValue(CommonFrozenFlowerBlock.LAYERS) != 0) {
//					cir.setReturnValue(true);
//				}
//				else {
//					cir.setReturnValue(false);
//				}
//			}
//		}
//	}
//
//	@Inject(method = "getDestroySpeed", at = @At("HEAD"), cancellable = true)
//	void getMiningSpeedMultiplier(ItemStack stack, BlockState state, CallbackInfoReturnable<Float> cir) {
//		if(blocks.equals(BlockTags.MINEABLE_WITH_SHOVEL)) {
//			if(state.is(CommonWinterlyBlocks.FROZEN_FLOWER)) {
//				if(state.getValue(CommonFrozenFlowerBlock.LAYERS) != 0) {
//					cir.setReturnValue(speed);
//				}
//				else {
//					cir.setReturnValue(1.0F);
//				}
//			}
//		}
//	}
}
