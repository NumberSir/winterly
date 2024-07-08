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

import static ru.pinkgoosik.winterly.registry.CommonWinterlyItems.*;

@SuppressWarnings("unused")
public class WinterlyItems {

    public static void init() {
        RED_CANDY_CANE = add("red_candy_cane", new Item(settings().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).fast().build())));
        GREEN_CANDY_CANE = add("green_candy_cane", new Item(settings().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).fast().build())));
        BLUE_CANDY_CANE = add("blue_candy_cane", new Item(settings().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).fast().build())));

        CRYOMARBLE_SHARD = add("cryomarble_shard", new Item(settings()));
        CRYOMARBLE = add("cryomarble", new Item(settings()));

        CRYOMARBLE_SWORD = add("cryomarble_sword", new CryomarbleSwordItem(Tiers.DIAMOND, settings()));
        CRYOMARBLE_SHOVEL = add("cryomarble_shovel", new CryomarbleShovelItem(Tiers.DIAMOND, settings()));
        CRYOMARBLE_PICKAXE = add("cryomarble_pickaxe", new CryomarblePickaxeItem(Tiers.DIAMOND, settings()));
        CRYOMARBLE_AXE = add("cryomarble_axe", new CryomarbleAxeItem(Tiers.DIAMOND, settings()));
        CRYOMARBLE_HOE = add("cryomarble_hoe", new CryomarbleHoeItem(Tiers.DIAMOND, settings()));

        RED_SANTA_HAT = add("red_santa_hat", new SantaHatItem(settings(), "red"));
        BLUE_SANTA_HAT = add("blue_santa_hat", new SantaHatItem(settings(), "blue"));

        WHITE_SCARF = add("white_scarf", new ScarfItem(settings(), "white"));
        RED_SCARF = add("red_scarf", new ScarfItem(settings(), "red"));
        GREEN_SCARF = add("green_scarf", new ScarfItem(settings(), "green"));
        BLUE_SCARF = add("blue_scarf", new ScarfItem(settings(), "blue"));
        RAINBOW_SCARF = add("rainbow_scarf", new ScarfItem(settings(), "rainbow"));

        ITEMS.forEach((id, item) -> Registry.register(BuiltInRegistries.ITEM, id, item));
    }

    private static <T extends Item> T add(String name, T item) {
        ITEMS.put(Winterly.id(name), item);
        return item;
    }
}
