package ru.pinkgoosik.winterly.fabric.registry;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import ru.pinkgoosik.winterly.Winterly;
import ru.pinkgoosik.winterly.worldgen.CryomarbleFeature;
import ru.pinkgoosik.winterly.worldgen.UndergroundIcicleFeature;

import static ru.pinkgoosik.winterly.Winterly.id;

public class WinterlyFeatures {
	public static final Feature<NoneFeatureConfiguration> UNDERGROUND_ICICLE_FEATURE = new UndergroundIcicleFeature();
	public static final ResourceKey<ConfiguredFeature<?,?>> UNDERGROUND_ICICLE_CONFIG = ResourceKey.create(Registries.CONFIGURED_FEATURE, Winterly.id("underground_icicle"));
	public static final ResourceKey<PlacedFeature> UNDERGROUND_ICICLE_PLACED = ResourceKey.create(Registries.PLACED_FEATURE, Winterly.id("underground_icicle"));


	public static final Feature<NoneFeatureConfiguration> CRYOMARBLE_FEATURE = new CryomarbleFeature();
	public static final ResourceKey<ConfiguredFeature<?,?>> CRYOMARBLE_CONFIG = ResourceKey.create(Registries.CONFIGURED_FEATURE, Winterly.id("cryomarble"));
	public static final ResourceKey<PlacedFeature> CRYOMARBLE_PLACED = ResourceKey.create(Registries.PLACED_FEATURE, Winterly.id("cryomarble"));

	public static void init() {
		Registry.register(BuiltInRegistries.FEATURE, id("underground_icicle"), UNDERGROUND_ICICLE_FEATURE);
		Registry.register(BuiltInRegistries.FEATURE, id("cryomarble"), CRYOMARBLE_FEATURE);

		BiomeModifications.create(Winterly.id("features"))
				.add(ModificationPhase.ADDITIONS, ctx -> {
					var entry = ctx.getBiomeRegistryEntry();
					return !entry.is(ConventionalBiomeTags.IS_NETHER) && !entry.is(ConventionalBiomeTags.IS_END) && entry.is(ConventionalBiomeTags.IS_COLD_OVERWORLD);
				}, ctx -> {
					ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, UNDERGROUND_ICICLE_PLACED);
				})
				.add(ModificationPhase.ADDITIONS, ctx -> {
					var entry = ctx.getBiomeRegistryEntry();
					return !entry.is(ConventionalBiomeTags.IS_NETHER) && !entry.is(ConventionalBiomeTags.IS_END);
				}, ctx -> {
					ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, CRYOMARBLE_PLACED);
				});
	}
}
