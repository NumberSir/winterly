package ru.pinkgoosik.winterly.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.DrownedRenderer;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.core.registries.BuiltInRegistries;
import ru.pinkgoosik.winterly.Winterly;
import ru.pinkgoosik.winterly.fabric.compat.WinterlyTrinketsIntegration;
import ru.pinkgoosik.winterly.block.*;
import ru.pinkgoosik.winterly.client.WinterlyModelLayers;
import ru.pinkgoosik.winterly.client.model.SantaHatModel;
import ru.pinkgoosik.winterly.client.model.ScarfModel;
import ru.pinkgoosik.winterly.client.render.DecorationFeatureRenderer;
import ru.pinkgoosik.winterly.client.render.MobDecorations;
import ru.pinkgoosik.winterly.item.CommonSantaHatItem;
import ru.pinkgoosik.winterly.item.CommonScarfItem;
import ru.pinkgoosik.winterly.registry.CommonWinterlyBlocks;
import ru.pinkgoosik.winterly.registry.CommonWinterlyItems;

import static net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry.registerModelLayer;

public class WinterlyFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        registerModelLayer(WinterlyModelLayers.SANTA_HAT_LAYER, SantaHatModel::getTexturedModelData);
        registerModelLayer(WinterlyModelLayers.SCARF_LAYER, ScarfModel::getTexturedModelData);

        MobDecorations.init();

        BlockRenderLayerMap map = BlockRenderLayerMap.INSTANCE;
        CommonWinterlyBlocks.BLOCKS.forEach((id, sup) -> {
			var block = BuiltInRegistries.BLOCK.get(id);

			if(block instanceof GiftBoxBlock) map.putBlock(block, RenderType.cutout());
            if(block instanceof GarlandLightsBlock) map.putBlock(block, RenderType.cutout());
            if(block instanceof SnowguyBlock) map.putBlock(block, RenderType.cutout());
            if(block instanceof IcicleBlock) map.putBlock(block, RenderType.cutout());
			if(block instanceof CommonFrozenGrassBlock) map.putBlock(block, RenderType.cutout());
			if(block instanceof CommonFrozenFlowerBlock) map.putBlock(block, RenderType.cutout());
        });
        map.putBlock(BuiltInRegistries.BLOCK.get(Winterly.id("icicle_block")), RenderType.translucent());
        map.putBlock(BuiltInRegistries.BLOCK.get(Winterly.id("icicle_pane")), RenderType.translucent());
        map.putBlock(BuiltInRegistries.BLOCK.get(Winterly.id("icicle_bars")), RenderType.cutout());

		if(FabricLoader.getInstance().isModLoaded("trinkets")) {
			CommonWinterlyItems.ITEMS.forEach((id, sup) -> {
				if(BuiltInRegistries.ITEM.get(id) instanceof CommonScarfItem scarf) {
					WinterlyTrinketsIntegration.registerScarfRenderer(scarf);
				}
				if(BuiltInRegistries.ITEM.get(id) instanceof CommonSantaHatItem hat) {
					WinterlyTrinketsIntegration.registerSantaHatRenderer(hat);
				}
			});
		}

        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
            if(entityRenderer instanceof ZombieRenderer renderer) {
                registrationHelper.register(new DecorationFeatureRenderer<>(renderer));
            }
            if(entityRenderer instanceof DrownedRenderer renderer) {
                registrationHelper.register(new DecorationFeatureRenderer<>(renderer));
            }
            if(entityRenderer instanceof SkeletonRenderer renderer) {
                registrationHelper.register(new DecorationFeatureRenderer<>(renderer));
            }
        });
    }

}
