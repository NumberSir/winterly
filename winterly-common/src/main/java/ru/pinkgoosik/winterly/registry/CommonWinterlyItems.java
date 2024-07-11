package ru.pinkgoosik.winterly.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import ru.pinkgoosik.winterly.Winterly;
import ru.pinkgoosik.winterly.item.tool.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class CommonWinterlyItems {
    public static final Map<ResourceLocation, Supplier<Item>> ITEMS = new LinkedHashMap<>();

	public static void init() {
		add("red_candy_cane", () -> new Item(settings().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).fast().build())));
	    add("green_candy_cane", () -> new Item(settings().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).fast().build())));
		add("blue_candy_cane", () -> new Item(settings().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).fast().build())));

		add("cryomarble_shard", () -> new Item(settings()));
		add("cryomarble", () -> new Item(settings()));

		add("cryomarble_sword", () -> new CryomarbleSwordItem(Tiers.DIAMOND, settings()));
		add("cryomarble_shovel", () -> new CryomarbleShovelItem(Tiers.DIAMOND, settings()));
	    add("cryomarble_pickaxe", () -> new CryomarblePickaxeItem(Tiers.DIAMOND, settings()));

		add("cryomarble_axe", () -> new CryomarbleAxeItem(Tiers.DIAMOND, settings()));
		add("cryomarble_hoe", () -> new CryomarbleHoeItem(Tiers.DIAMOND, settings()));
	}

	public static Item.Properties settings() {
		return new Item.Properties();
	}

	public static void add(String name, Supplier<Item> item) {
		ITEMS.put(Winterly.id(name), item);
	}
}

