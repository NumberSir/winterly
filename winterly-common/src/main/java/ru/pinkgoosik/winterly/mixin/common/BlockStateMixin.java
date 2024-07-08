package ru.pinkgoosik.winterly.mixin.common;

import com.mojang.serialization.MapCodec;
import it.unimi.dsi.fastutil.objects.Reference2ObjectArrayMap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateHolder;
import net.minecraft.world.level.block.state.properties.Property;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.pinkgoosik.winterly.block.CommonFrozenFlowerBlock;
import ru.pinkgoosik.winterly.registry.CommonWinterlyBlocks;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class BlockStateMixin extends StateHolder<Block, BlockState> {

	protected BlockStateMixin(Block owner, Reference2ObjectArrayMap<Property<?>, Comparable<?>> values, MapCodec<BlockState> propertiesCodec) {
		super(owner, values, propertiesCodec);
	}

	@Shadow
	public abstract boolean is(Block block);

	@Shadow
	@Final
	private float destroySpeed;

	@Inject(method = "getDestroySpeed", at = @At("HEAD"), cancellable = true)
	void getHardness(BlockGetter world, BlockPos pos, CallbackInfoReturnable<Float> cir) {
		if(is(CommonWinterlyBlocks.FROZEN_FLOWER)) {
			if(getValue(CommonFrozenFlowerBlock.LAYERS) == 0) {
				cir.setReturnValue(0.0F);
			}
			else {
				cir.setReturnValue(destroySpeed);
			}
		}
	}

	@Inject(method = "requiresCorrectToolForDrops", at = @At("HEAD"), cancellable = true)
	void isToolRequired(CallbackInfoReturnable<Boolean> cir) {
		if(is(CommonWinterlyBlocks.FROZEN_FLOWER)) {
			if(getValue(CommonFrozenFlowerBlock.LAYERS) == 0) {
				cir.setReturnValue(false);
			}
			else {
				cir.setReturnValue(true);
			}
		}
	}
}
