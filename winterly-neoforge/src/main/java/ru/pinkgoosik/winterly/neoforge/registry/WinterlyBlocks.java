package ru.pinkgoosik.winterly.neoforge.registry;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import ru.pinkgoosik.winterly.block.*;
import ru.pinkgoosik.winterly.registry.CommonWinterlyBlocks;
import static ru.pinkgoosik.winterly.registry.CommonWinterlyBlocks.*;

public class WinterlyBlocks {

    public static void init() {
		CommonWinterlyBlocks.init();

		add("frozen_grass", () -> new CommonFrozenGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).replaceable().forceSolidOff().randomTicks().strength(0.1F).requiresCorrectToolForDrops().sound(SoundType.SNOW).isViewBlocking((state, world, pos) -> state.getValue(CommonFrozenGrassBlock.LAYERS) >= 8).pushReaction(PushReaction.DESTROY)));
		add("frozen_flower", () -> new CommonFrozenFlowerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).replaceable().forceSolidOff().randomTicks().strength(0.1F).requiresCorrectToolForDrops().sound(SoundType.GRASS).isViewBlocking((state, world, pos) -> state.getValue(CommonFrozenFlowerBlock.LAYERS) >= 8).pushReaction(PushReaction.DESTROY)));
    }

}
