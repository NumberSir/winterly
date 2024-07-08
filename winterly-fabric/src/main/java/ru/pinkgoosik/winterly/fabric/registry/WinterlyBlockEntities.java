package ru.pinkgoosik.winterly.fabric.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import ru.pinkgoosik.winterly.Winterly;
import ru.pinkgoosik.winterly.block.GiftBoxBlock;
import ru.pinkgoosik.winterly.block.entity.GiftBoxBlockEntity;
import ru.pinkgoosik.winterly.registry.CommonWinterlyBlockEntities;
import ru.pinkgoosik.winterly.registry.CommonWinterlyBlocks;

import java.util.ArrayList;

public class WinterlyBlockEntities {

	public static void init() {
		CommonWinterlyBlockEntities.GIFT_BOX_BLOCK_ENTITY = Registry.register(
				BuiltInRegistries.BLOCK_ENTITY_TYPE,
				Winterly.id("gift_box"),
				FabricBlockEntityTypeBuilder.create(GiftBoxBlockEntity::new, getGiftBoxes()).build()
		);
	}

	public static GiftBoxBlock[] getGiftBoxes() {
		ArrayList<GiftBoxBlock> gifts = new ArrayList<>();

		CommonWinterlyBlocks.BLOCKS.forEach((identifier, block) -> {
			if(block instanceof GiftBoxBlock box) {
				gifts.add(box);
			}
		});

		return gifts.toArray(new GiftBoxBlock[0]);
	}
}
