package ru.pinkgoosik.winterly.neoforge.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.RegisterEvent;
import ru.pinkgoosik.winterly.Winterly;
import ru.pinkgoosik.winterly.block.GiftBoxBlock;
import ru.pinkgoosik.winterly.block.entity.GiftBoxBlockEntity;
import ru.pinkgoosik.winterly.registry.CommonWinterlyBlocks;

import java.util.ArrayList;


public class WinterlyBlockEntities {

	public static void init(RegisterEvent.RegisterHelper<BlockEntityType<?>> registry) {
		registry.register(Winterly.id("gift_box"), BlockEntityType.Builder.of(GiftBoxBlockEntity::new, getGiftBoxes()).build(null));
	}

	public static GiftBoxBlock[] getGiftBoxes() {
		ArrayList<GiftBoxBlock> gifts = new ArrayList<>();

		CommonWinterlyBlocks.BLOCKS.forEach((id, sup) -> {
			if(BuiltInRegistries.BLOCK.get(id) instanceof GiftBoxBlock box) {
				gifts.add(box);
			}
		});

		return gifts.toArray(new GiftBoxBlock[0]);
	}
}
