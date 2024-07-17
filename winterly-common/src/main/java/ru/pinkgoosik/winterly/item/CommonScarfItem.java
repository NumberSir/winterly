package ru.pinkgoosik.winterly.item;

import net.minecraft.world.item.Item;

public class CommonScarfItem extends Item {
    public final String color;

    public CommonScarfItem(Item.Properties settings, String color) {
        super(settings);
        this.color = color;
    }
}
