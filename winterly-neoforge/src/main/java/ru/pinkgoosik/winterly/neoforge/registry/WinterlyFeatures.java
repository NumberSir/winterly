package ru.pinkgoosik.winterly.neoforge.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import ru.pinkgoosik.winterly.worldgen.CryomarbleFeature;
import ru.pinkgoosik.winterly.worldgen.UndergroundIcicleFeature;

public class WinterlyFeatures {

    public static final DeferredRegister<Feature<?>> REGISTERER = DeferredRegister.create(BuiltInRegistries.FEATURE, "winterly");

    public static void init(IEventBus eventBus) {
        REGISTERER.register("underground_icicle", UndergroundIcicleFeature::new);
        REGISTERER.register("cryomarble", CryomarbleFeature::new);

        REGISTERER.register(eventBus);
    }
}
