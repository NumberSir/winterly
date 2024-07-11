package ru.pinkgoosik.winterly.data;

import net.minecraft.core.HolderLookup;

import java.util.ArrayList;
import java.util.Optional;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import ru.pinkgoosik.winterly.block.entity.GiftBoxBlockEntityData;

public class GiftBoxData {
	public ArrayList<ItemStack> stacks = new ArrayList<>();

	public static GiftBoxData fromNbt(CompoundTag nbt, HolderLookup.Provider registries) {
		GiftBoxData gift = new GiftBoxData();

		int size = nbt.getInt("size");
		for(int i = 0; i < size; i++) {
			CompoundTag entryNbt = nbt.getCompound(String.valueOf(i));
			Optional<ItemStack> stack = ItemStack.parse(registries, entryNbt.getCompound("itemstack"));
            stack.ifPresent(itemStack -> gift.stacks.add(itemStack));
		}

		return gift;
	}

	public static CompoundTag toNbt(GiftBoxBlockEntityData entity, HolderLookup.Provider registries) {
		CompoundTag nbt = new CompoundTag();
		nbt.putInt("size", entity.getStacks().size());

		for(int i = 0; i < entity.getStacks().size(); i++) {
			ItemStack stack = entity.getStacks().get(i);
			CompoundTag entryNbt = new CompoundTag();
			entryNbt.put("itemstack", stack.save(registries));

			nbt.put(Integer.toString(i), entryNbt);
		}
		return nbt;
	}
}
