package ru.pinkgoosik.winterly.fabric.data;

import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistryV3;
import org.ladysnake.cca.api.v3.world.WorldComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.world.WorldComponentInitializer;
import ru.pinkgoosik.winterly.Winterly;

public class WinterlyComponents implements WorldComponentInitializer {
	public static final ComponentKey<WorldData> WORLD_DATA = ComponentRegistryV3.INSTANCE.getOrCreate(Winterly.id("world_data"), WorldData.class);

	@Override
	public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {
		registry.register(WORLD_DATA, WorldData::new);
	}
}
