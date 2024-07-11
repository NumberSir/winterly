package ru.pinkgoosik.winterly.fabric.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import ru.pinkgoosik.winterly.fabric.item.SantaHatItem;
import ru.pinkgoosik.winterly.fabric.item.ScarfItem;
import ru.pinkgoosik.winterly.Winterly;
import ru.pinkgoosik.winterly.item.tool.*;
import ru.pinkgoosik.winterly.registry.CommonWinterlyItems;

import static ru.pinkgoosik.winterly.registry.CommonWinterlyItems.*;

@SuppressWarnings("unused")
public class WinterlyItems {

    public static void init() {
		CommonWinterlyItems.init();
//        RED_CANDY_CANE = add("red_candy_cane", new Item(settings().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).fast().build())));
//        GREEN_CANDY_CANE = add("green_candy_cane", new Item(settings().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).fast().build())));
//        BLUE_CANDY_CANE = add("blue_candy_cane", new Item(settings().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).fast().build())));
//
//        CRYOMARBLE_SHARD = add("cryomarble_shard", new Item(settings()));
//        CRYOMARBLE = add("cryomarble", new Item(settings()));
//
//        CRYOMARBLE_SWORD = add("cryomarble_sword", new CryomarbleSwordItem(Tiers.DIAMOND, settings()));
//        CRYOMARBLE_SHOVEL = add("cryomarble_shovel", new CryomarbleShovelItem(Tiers.DIAMOND, settings()));
//        CRYOMARBLE_PICKAXE = add("cryomarble_pickaxe", new CryomarblePickaxeItem(Tiers.DIAMOND, settings()));
//        CRYOMARBLE_AXE = add("cryomarble_axe", new CryomarbleAxeItem(Tiers.DIAMOND, settings()));
//        CRYOMARBLE_HOE = add("cryomarble_hoe", new CryomarbleHoeItem(Tiers.DIAMOND, settings()));
//
        add("red_santa_hat", () -> new SantaHatItem(settings(), "red"));
        add("blue_santa_hat", () -> new SantaHatItem(settings(), "blue"));

        add("white_scarf", () -> new ScarfItem(settings(), "white"));
        add("red_scarf", () -> new ScarfItem(settings(), "red"));
        add("green_scarf", () -> new ScarfItem(settings(), "green"));
        add("blue_scarf", () -> new ScarfItem(settings(), "blue"));
        add("rainbow_scarf", () -> new ScarfItem(settings(), "rainbow"));

        ITEMS.forEach((id, sup) -> Registry.register(BuiltInRegistries.ITEM, id, sup.get()));
    }
}
