package ru.pinkgoosik.winterly.neoforge.registry;

import ru.pinkgoosik.winterly.neoforge.item.SantaHatItem;
import ru.pinkgoosik.winterly.neoforge.item.ScarfItem;
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
    }

}
