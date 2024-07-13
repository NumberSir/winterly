package ru.pinkgoosik.winterly.data;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class CachedFlowers {
	public static CachedFlowers instance = new CachedFlowers();

	public static Block getFlower(Level world, BlockPos pos) {
		return instance.getFlowerImpl(world, pos);
	}

	@Nullable
	public Block getFlowerImpl(Level world, BlockPos pos) {
		throw new UnsupportedOperationException();
    }

	public static void cacheFlower(Level world, BlockPos pos, Block flower) {
		instance.cacheFlowerImpl(world, pos, flower);
	}

	public void cacheFlowerImpl(Level world, BlockPos pos, Block flower) {
		throw new UnsupportedOperationException();
	}
}
