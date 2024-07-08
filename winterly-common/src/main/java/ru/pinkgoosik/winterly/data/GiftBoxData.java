package ru.pinkgoosik.winterly.data;

import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CustomData;

import java.util.ArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import ru.pinkgoosik.winterly.block.entity.GiftBoxBlockEntityData;

public class GiftBoxData {
	public ArrayList<ItemStack> stacks = new ArrayList<>();

	public static GiftBoxData fromNbt(CompoundTag nbt) {
		GiftBoxData gift = new GiftBoxData();

		int size = nbt.getInt("size");
		for(int i = 0; i < size; i++) {
			CompoundTag entryNbt = nbt.getCompound(String.valueOf(i));

			var item = BuiltInRegistries.ITEM.getOptional(ResourceLocation.parse(entryNbt.getString("item")));
			if(item.isPresent()) {
				ItemStack temp = new ItemStack(item.get(), entryNbt.getInt("count"));

				if(entryNbt.contains("nbt")) {
					temp.applyComponents(DataComponentMap.builder().set(DataComponents.CUSTOM_DATA, CustomData.of((CompoundTag) entryNbt.get("nbt"))).build());
				}

				gift.stacks.add(temp);
			}
		}

		return gift;
	}

	public static CompoundTag toNbt(GiftBoxBlockEntityData entity) {
		CompoundTag nbt = new CompoundTag();
		nbt.putInt("size", entity.getStacks().size());

		for(int i = 0; i < entity.getStacks().size(); i++) {
			ItemStack stack = entity.getStacks().get(i);
			CompoundTag entryNbt = new CompoundTag();
			entryNbt.putString("item", BuiltInRegistries.ITEM.getKey(stack.getItem()).toString());

			if(stack.getComponents().has(DataComponents.CUSTOM_DATA)) {
				entryNbt.put("nbt", stack.getComponents().get(DataComponents.CUSTOM_DATA).copyTag());
			}

			entryNbt.putInt("count", stack.getCount());

			nbt.put(Integer.toString(i), entryNbt);
		}
		return nbt;
	}
}
