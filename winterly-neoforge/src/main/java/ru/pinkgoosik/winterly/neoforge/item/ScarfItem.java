package ru.pinkgoosik.winterly.neoforge.item;

import net.minecraft.ChatFormatting;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.fml.ModList;
import ru.pinkgoosik.winterly.neoforge.compat.WinterlyCuriosIntegration;
import ru.pinkgoosik.winterly.item.CommonScarfItem;

import java.util.List;

@SuppressWarnings("NullableProblems")
public class ScarfItem extends CommonScarfItem {

    public ScarfItem(Item.Properties settings, String color) {
        super(settings, color);

		if(ModList.get().isLoaded("curios")) {
			WinterlyCuriosIntegration.registerCurio(this);
		}
    }

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		if(!ModList.get().isLoaded("curios")) {
			Language lang = Language.getInstance();
			String key = "tip.winterly.requires_curios.";

			for(int i = 0; i <= 32; i++) {
				if(lang.has(key + i)) {
					tooltip.add(Component.translatable(key + i).toFlatList(Style.EMPTY.withColor(ChatFormatting.GRAY)).getFirst());
				}
				if(!lang.has(key + (i + 1))) {
					break;
				}
			}
		}
    }
}
