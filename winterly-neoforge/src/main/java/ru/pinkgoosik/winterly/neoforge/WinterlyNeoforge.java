package ru.pinkgoosik.winterly.neoforge;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import ru.pinkgoosik.winterly.neoforge.client.WinterlyNeoforgeClient;
import ru.pinkgoosik.winterly.neoforge.data.WinterlyDataAttachments;
import ru.pinkgoosik.winterly.neoforge.registry.WinterlyBlockEntities;
import ru.pinkgoosik.winterly.neoforge.registry.WinterlyBlocks;
import ru.pinkgoosik.winterly.neoforge.registry.WinterlyFeatures;
import ru.pinkgoosik.winterly.neoforge.registry.WinterlyItems;
import ru.pinkgoosik.winterly.Winterly;
import ru.pinkgoosik.winterly.registry.CommonWinterlyBlocks;
import ru.pinkgoosik.winterly.registry.CommonWinterlyItems;

import java.util.function.Supplier;

@SuppressWarnings("unused")
@Mod(Winterly.MOD_ID)
public class WinterlyNeoforge {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "winterly");

    public static final Supplier<CreativeModeTab> WINTERLY_TAB = CREATIVE_MODE_TABS.register("winterly", () -> CreativeModeTab.builder().icon(CommonWinterlyBlocks.SNOWGUY.asItem()::getDefaultInstance).title(Component.translatable("itemGroup.winterly.items")).build());

    public WinterlyNeoforge(IEventBus bus) {
        CREATIVE_MODE_TABS.register(bus);

        WinterlyItems.init(bus);
        WinterlyBlocks.init(bus);
        WinterlyBlockEntities.init(bus);
        WinterlyFeatures.init(bus);
        WinterlyDataAttachments.init(bus);

        bus.addListener(this::buildCreativeTab);
		bus.addListener(this::commonSetup);

		if(FMLEnvironment.dist.isClient()) {
			WinterlyNeoforgeClient.init(bus);
		}
    }

    private void buildCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == WINTERLY_TAB.get()) {
            CommonWinterlyItems.ITEMS.forEach((id, item) -> event.accept(item));
            CommonWinterlyBlocks.ITEMS.forEach((id, item) -> event.accept(item));
        }
    }

	private void commonSetup(FMLCommonSetupEvent event) {

    }
}
