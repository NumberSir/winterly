package ru.pinkgoosik.winterly.neoforge.client;

import net.minecraft.client.renderer.entity.DrownedRenderer;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.world.entity.EntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import ru.pinkgoosik.winterly.neoforge.compat.WinterlyCuriosIntegration;
import ru.pinkgoosik.winterly.client.WinterlyModelLayers;
import ru.pinkgoosik.winterly.client.model.SantaHatModel;
import ru.pinkgoosik.winterly.client.model.ScarfModel;
import ru.pinkgoosik.winterly.client.render.DecorationFeatureRenderer;
import ru.pinkgoosik.winterly.client.render.MobDecorations;
import ru.pinkgoosik.winterly.config.WinterlyClientConfig;
import ru.pinkgoosik.winterly.item.CommonSantaHatItem;
import ru.pinkgoosik.winterly.item.CommonScarfItem;
import ru.pinkgoosik.winterly.registry.CommonWinterlyItems;

@OnlyIn(Dist.CLIENT)
public class WinterlyNeoforgeClient {

	public static void init(IEventBus bus) {
		bus.addListener(WinterlyNeoforgeClient::clientSetup);
		bus.addListener(WinterlyNeoforgeClient::registerModelLayers);
		bus.addListener(WinterlyNeoforgeClient::registerRenderLayers);
	}

	private static void registerModelLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(WinterlyModelLayers.SANTA_HAT_LAYER, SantaHatModel::getTexturedModelData);
		event.registerLayerDefinition(WinterlyModelLayers.SCARF_LAYER, ScarfModel::getTexturedModelData);
	}

	private static void registerRenderLayers(EntityRenderersEvent.AddLayers event) {

		if(event.getRenderer(EntityType.ZOMBIE) instanceof ZombieRenderer renderer) {
			renderer.addLayer(new DecorationFeatureRenderer<>(renderer));
		}
		if(event.getRenderer(EntityType.DROWNED) instanceof DrownedRenderer renderer) {
			renderer.addLayer(new DecorationFeatureRenderer<>(renderer));
		}
		if(event.getRenderer(EntityType.SKELETON) instanceof SkeletonRenderer renderer) {
			renderer.addLayer(new DecorationFeatureRenderer<>(renderer));
		}
	}

	private static void clientSetup(FMLClientSetupEvent event) {
		if(ModList.get().isLoaded("curios")) {
			CommonWinterlyItems.ITEMS.forEach((resourceLocation, item) -> {
				if(item instanceof CommonScarfItem scarf) WinterlyCuriosIntegration.registerScarfRenderer(scarf);
				if(item instanceof CommonSantaHatItem hat) WinterlyCuriosIntegration.registerSantaHatRenderer(hat);
			});
		}

		MobDecorations.init();
		ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> (client, screen) -> WinterlyClientConfig.buildScreen(screen));
	}
}
