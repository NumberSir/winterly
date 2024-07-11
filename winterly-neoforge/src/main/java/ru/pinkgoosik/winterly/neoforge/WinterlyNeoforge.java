package ru.pinkgoosik.winterly.neoforge;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import ru.pinkgoosik.winterly.neoforge.client.WinterlyNeoforgeClient;
import ru.pinkgoosik.winterly.neoforge.data.WinterlyDataAttachments;
import ru.pinkgoosik.winterly.neoforge.registry.WinterlyBlockEntities;
import ru.pinkgoosik.winterly.neoforge.registry.WinterlyBlocks;
import ru.pinkgoosik.winterly.neoforge.registry.WinterlyFeatures;
import ru.pinkgoosik.winterly.neoforge.registry.WinterlyItems;
import ru.pinkgoosik.winterly.Winterly;
import ru.pinkgoosik.winterly.registry.CommonWinterlyBlocks;
import ru.pinkgoosik.winterly.registry.CommonWinterlyItems;

import static ru.pinkgoosik.winterly.registry.CommonWinterlyItems.ITEMS;

@SuppressWarnings("unused")
@Mod(Winterly.MOD_ID)
public class WinterlyNeoforge {

    public WinterlyNeoforge(IEventBus bus) {
        WinterlyFeatures.init(bus);
        WinterlyDataAttachments.init(bus);

		bus.addListener(this::register);
        bus.addListener(this::buildCreativeTab);
		bus.addListener(this::commonSetup);

		if(FMLEnvironment.dist.isClient()) {
			WinterlyNeoforgeClient.init(bus);
		}
    }

	public void register(RegisterEvent event) {
		WinterlyItems.init();
		WinterlyBlocks.init();

		event.register(Registries.CREATIVE_MODE_TAB, registry -> {
			registry.register(Winterly.id("items"), CreativeModeTab.builder().icon(BuiltInRegistries.ITEM.get(Winterly.id("snowguy"))::getDefaultInstance).title(Component.translatable("itemGroup.winterly.items")).build());
		});

		event.register(Registries.ITEM, registry -> ITEMS.forEach((id, sup) -> registry.register(id, sup.get())));
		event.register(Registries.BLOCK, registry -> CommonWinterlyBlocks.BLOCKS.forEach((id, sup) -> registry.register(id, sup.get())));
		event.register(Registries.ITEM, registry -> CommonWinterlyBlocks.BLOCKS.forEach((id, sup) -> registry.register(id, new BlockItem(BuiltInRegistries.BLOCK.get(id), new Item.Properties()))));
		event.register(Registries.BLOCK_ENTITY_TYPE, WinterlyBlockEntities::init);
	}

    private void buildCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == BuiltInRegistries.CREATIVE_MODE_TAB.get(Winterly.id("items"))) {
            CommonWinterlyItems.ITEMS.forEach((id, sup) -> event.accept(BuiltInRegistries.ITEM.get(id)));
            CommonWinterlyBlocks.BLOCKS.forEach((id, sup) -> event.accept(BuiltInRegistries.ITEM.get(id)));
        }
    }

	private void commonSetup(FMLCommonSetupEvent event) {

    }
}
