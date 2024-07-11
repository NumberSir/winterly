package ru.pinkgoosik.winterly.fabric.item;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.ChatFormatting;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import ru.pinkgoosik.winterly.fabric.compat.WinterlyTrinketsIntegration;
import ru.pinkgoosik.winterly.item.CommonSantaHatItem;

import java.util.List;

@SuppressWarnings("NullableProblems")
public class SantaHatItem extends CommonSantaHatItem {

    public SantaHatItem(Item.Properties settings, String color) {
        super(settings, color);
		if(FabricLoader.getInstance().isModLoaded("trinkets")) {
			WinterlyTrinketsIntegration.registerTrinket(this);
		}
    }

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(Component.translatable("tag.winterly.cosmetic").withStyle(ChatFormatting.GRAY));
		tooltip.add(Component.nullToEmpty(" "));

		if(!FabricLoader.getInstance().isModLoaded("trinkets")) {
			Language lang = Language.getInstance();
			String key = "tip.winterly.requires_trinkets.";

			for(int i = 0; i <= 32; i++) {
				if(lang.has(key + i)) {
					tooltip.add(Component.translatable(key + i).toFlatList(Style.EMPTY.withColor(ChatFormatting.GRAY)).get(0));
				}
				if(!lang.has(key + (i + 1))) {
					break;
				}
			}
		}
	}
}
