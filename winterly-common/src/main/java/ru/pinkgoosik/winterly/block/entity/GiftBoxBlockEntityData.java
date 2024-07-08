package ru.pinkgoosik.winterly.block.entity;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;

public interface GiftBoxBlockEntityData {
    ArrayList<ItemStack> getStacks();
    void saveAdditional(CompoundTag nbt, HolderLookup.Provider registries);
    void loadAdditional(CompoundTag nbt, HolderLookup.Provider registries);
}
