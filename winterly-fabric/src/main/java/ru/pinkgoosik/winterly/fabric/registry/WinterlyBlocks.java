package ru.pinkgoosik.winterly.fabric.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import ru.pinkgoosik.winterly.block.*;
import ru.pinkgoosik.winterly.fabric.block.FrozenFlowerBlock;
import ru.pinkgoosik.winterly.fabric.block.FrozenGrassBlock;
import ru.pinkgoosik.winterly.registry.CommonWinterlyBlocks;

import static ru.pinkgoosik.winterly.registry.CommonWinterlyBlocks.*;

public class WinterlyBlocks {

    public static void init() {
		CommonWinterlyBlocks.init();

        add("frozen_grass", () -> new FrozenGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).replaceable().forceSolidOff().randomTicks().strength(0.1F).requiresCorrectToolForDrops().sound(SoundType.SNOW).isViewBlocking((state, world, pos) -> state.getValue(CommonFrozenGrassBlock.LAYERS) >= 8).pushReaction(PushReaction.DESTROY)));
        add("frozen_flower", () -> new FrozenFlowerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).replaceable().forceSolidOff().randomTicks().strength(0.1F).requiresCorrectToolForDrops().sound(SoundType.GRASS).isViewBlocking((state, world, pos) -> state.getValue(CommonFrozenFlowerBlock.LAYERS) >= 8).pushReaction(PushReaction.DESTROY)));

        BLOCKS.forEach((id, sup) -> {
			var block = sup.get();
			Registry.register(BuiltInRegistries.BLOCK, id, block);
			Registry.register(BuiltInRegistries.ITEM, id, new BlockItem(block, new Item.Properties()));
		});
    }

}
