package ru.pinkgoosik.winterly.block.entity;

import net.minecraft.core.HolderLookup;
import ru.pinkgoosik.winterly.data.GiftBoxData;
import ru.pinkgoosik.winterly.registry.CommonWinterlyBlockEntities;

import java.util.ArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class GiftBoxBlockEntity extends BlockEntity implements GiftBoxBlockEntityData {
    public ArrayList<ItemStack> stacks = new ArrayList<>();

    public GiftBoxBlockEntity(BlockPos pos, BlockState state) {
        super(CommonWinterlyBlockEntities.GIFT_BOX_BLOCK_ENTITY, pos, state);
    }

    @Override
    public ArrayList<ItemStack> getStacks() {
        return this.stacks;
    }

    @Override
    public void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
		tag.put("giftBoxData", GiftBoxData.toNbt(this));
    }

	@Override
	public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.loadAdditional(tag, registries);
		var data = GiftBoxData.fromNbt(tag.getCompound("giftBoxData"));
		this.stacks = data.stacks;
	}

}
