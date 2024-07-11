package ru.pinkgoosik.winterly.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import ru.pinkgoosik.winterly.Winterly;
import ru.pinkgoosik.winterly.item.tool.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class CommonWinterlyItems {
    public static final Map<ResourceLocation, Supplier<Item>> ITEMS = new LinkedHashMap<>();

	public static void init() {
		add("red_candy_cane", () -> new Item(properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).fast().build())));
	    add("green_candy_cane", () -> new Item(properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).fast().build())));
		add("blue_candy_cane", () -> new Item(properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).fast().build())));

		add("cryomarble_shard", () -> new Item(properties()));
		add("cryomarble", () -> new Item(properties()));

		add("cryomarble_sword", () -> new CryomarbleSwordItem(Tiers.DIAMOND, properties().attributes(SwordItem.createAttributes(Tiers.DIAMOND, 3, -2.4F))));
		add("cryomarble_shovel", () -> new CryomarbleShovelItem(Tiers.DIAMOND, properties().attributes(ShovelItem.createAttributes(Tiers.DIAMOND, 1.5F, -3.0F))));
	    add("cryomarble_pickaxe", () -> new CryomarblePickaxeItem(Tiers.DIAMOND, properties().attributes(PickaxeItem.createAttributes(Tiers.DIAMOND, 1.0F, -2.8F))));

		add("cryomarble_axe", () -> new CryomarbleAxeItem(Tiers.DIAMOND, properties().attributes(AxeItem.createAttributes(Tiers.DIAMOND, 5.0F, -3.0F))));
		add("cryomarble_hoe", () -> new CryomarbleHoeItem(Tiers.DIAMOND, properties().attributes(HoeItem.createAttributes(Tiers.DIAMOND, -3.0F, 0.0F))));
	}

	public static Item.Properties properties() {
		return new Item.Properties();
	}

	public static void add(String name, Supplier<Item> item) {
		ITEMS.put(Winterly.id(name), item);
	}
}

