package ru.pinkgoosik.winterly.neoforge.registry;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.neoforged.neoforge.registries.RegisterEvent;
import ru.pinkgoosik.winterly.Winterly;
import ru.pinkgoosik.winterly.worldgen.CryomarbleFeature;
import ru.pinkgoosik.winterly.worldgen.UndergroundIcicleFeature;

public class WinterlyFeatures {

    public static void init(RegisterEvent.RegisterHelper<Feature<?>> registry) {
		registry.register(Winterly.id("underground_icicle"), new UndergroundIcicleFeature());
		registry.register(Winterly.id("cryomarble"), new CryomarbleFeature());
    }
}
