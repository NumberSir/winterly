package ru.pinkgoosik.winterly.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import ru.pinkgoosik.winterly.Winterly;
import ru.pinkgoosik.winterly.fabric.compat.WinterlyOwoLibIntegration;
import ru.pinkgoosik.winterly.fabric.registry.WinterlyBlockEntities;
import ru.pinkgoosik.winterly.fabric.registry.WinterlyBlocks;
import ru.pinkgoosik.winterly.registry.CommonWinterlyBlocks;
import ru.pinkgoosik.winterly.fabric.registry.WinterlyFeatures;
import ru.pinkgoosik.winterly.fabric.registry.WinterlyItems;
import ru.pinkgoosik.winterly.registry.CommonWinterlyItems;

public class WinterlyFabric implements ModInitializer {
    public static CreativeModeTab itemGroup;

    @Override
    public void onInitialize() {
		itemGroup = createItemGroup();
        WinterlyItems.init();

		WinterlyBlocks.init();
		WinterlyBlockEntities.init();
        WinterlyFeatures.init();

//		if(FabricLoader.getInstance().isModLoaded("owo")) {
//			WinterlyOwoLibIntegration.initItemGroup();
//			ItemGroupEvents.modifyEntriesEvent(BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(itemGroup).get()).register(entries -> {
//				CommonWinterlyItems.ITEMS.forEach((id, sup) -> entries.accept(BuiltInRegistries.ITEM.get(id)));
//				CommonWinterlyBlocks.BLOCKS.forEach((id, sup) -> entries.accept(BuiltInRegistries.ITEM.get(id)));
//			});
//		}
    }

    private static CreativeModeTab createItemGroup() {
//		if(FabricLoader.getInstance().isModLoaded("owo")) {
//			return WinterlyOwoLibIntegration.createItemGroup();
//		}
        var group = FabricItemGroup.builder().title(Component.translatable("itemGroup.winterly.items"))
			.icon(() -> BuiltInRegistries.ITEM.get(Winterly.id("snowguy")).getDefaultInstance())
			.displayItems((displayContext, entries) -> {
				CommonWinterlyItems.ITEMS.forEach((id, sup) -> entries.accept(BuiltInRegistries.ITEM.get(id)));
				CommonWinterlyBlocks.BLOCKS.forEach((id, sup) -> entries.accept(BuiltInRegistries.ITEM.get(id)));
			}).build();
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, Winterly.id("items"), group);
		return group;
    }

}
