package ru.pinkgoosik.winterly.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import ru.pinkgoosik.winterly.Winterly;

public class WinterlyModelLayers {
    public static final ModelLayerLocation SANTA_HAT_LAYER = of("santa_hat");
    public static final ModelLayerLocation SCARF_LAYER = of("scarf");

    private static ModelLayerLocation of(String name){
        return new ModelLayerLocation(Winterly.id(name), "main");
    }
}
