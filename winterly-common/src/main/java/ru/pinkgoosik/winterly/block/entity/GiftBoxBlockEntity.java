package ru.pinkgoosik.winterly.block.entity;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import ru.pinkgoosik.winterly.Winterly;
import ru.pinkgoosik.winterly.data.GiftBoxData;

import java.util.ArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class GiftBoxBlockEntity extends BlockEntity implements GiftBoxBlockEntityData {
    public ArrayList<ItemStack> stacks = new ArrayList<>();

    public GiftBoxBlockEntity(BlockPos pos, BlockState state) {
        super(BuiltInRegistries.BLOCK_ENTITY_TYPE.get(Winterly.id("gift_box")), pos, state);
    }

    @Override
    public ArrayList<ItemStack> getStacks() {
        return this.stacks;
    }

    @Override
    public void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
		tag.put("giftBoxData", GiftBoxData.toNbt(this, registries));
    }

	@Override
	public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.loadAdditional(tag, registries);
		var data = GiftBoxData.fromNbt(tag.getCompound("giftBoxData"), registries);
		this.stacks = data.stacks;
	}

}
