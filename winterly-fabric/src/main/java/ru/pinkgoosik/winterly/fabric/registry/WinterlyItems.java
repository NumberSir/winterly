package ru.pinkgoosik.winterly.fabric.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import ru.pinkgoosik.winterly.fabric.item.SantaHatItem;
import ru.pinkgoosik.winterly.fabric.item.ScarfItem;
import ru.pinkgoosik.winterly.registry.CommonWinterlyItems;

import static ru.pinkgoosik.winterly.registry.CommonWinterlyItems.*;

@SuppressWarnings("unused")
public class WinterlyItems {

    public static void init() {
		CommonWinterlyItems.init();
        add("red_santa_hat", () -> new SantaHatItem(properties(), "red"));
        add("blue_santa_hat", () -> new SantaHatItem(properties(), "blue"));

        add("white_scarf", () -> new ScarfItem(properties(), "white"));
        add("red_scarf", () -> new ScarfItem(properties(), "red"));
        add("green_scarf", () -> new ScarfItem(properties(), "green"));
        add("blue_scarf", () -> new ScarfItem(properties(), "blue"));
        add("rainbow_scarf", () -> new ScarfItem(properties(), "rainbow"));

        ITEMS.forEach((id, sup) -> Registry.register(BuiltInRegistries.ITEM, id, sup.get()));
    }
}
