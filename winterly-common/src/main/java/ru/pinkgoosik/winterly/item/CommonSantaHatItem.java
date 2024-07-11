package ru.pinkgoosik.winterly.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import java.util.List;

public class CommonSantaHatItem extends Item {
    public final String color;

    public CommonSantaHatItem(Item.Properties settings, String color) {
        super(settings);
        this.color = color;
    }

//	@Override
//	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
//		tooltip.add(Component.translatable("tag.winterly.cosmetic").withStyle(ChatFormatting.GRAY));
//		tooltip.add(Component.nullToEmpty(" "));
//		super.appendHoverText(stack, context, tooltip, tooltipFlag);
//	}
}
