package ru.pinkgoosik.winterly.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import ru.pinkgoosik.winterly.Winterly;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.PushReaction;
import ru.pinkgoosik.winterly.block.*;
import ru.pinkgoosik.winterly.block.base.BasePaneBlock;
import ru.pinkgoosik.winterly.block.base.BaseStairsBlock;
import ru.pinkgoosik.winterly.block.base.BaseTransparentBlock;

import static net.minecraft.world.level.block.Blocks.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public abstract class CommonWinterlyBlocks {
    public static final Map<ResourceLocation, Supplier<Block>> BLOCKS = new LinkedHashMap<>();

	public static void init() {
		add("icicle", () -> new IcicleBlock(copyOf(ICE).pushReaction(PushReaction.DESTROY)));
		add("icicle_block", () -> new BaseTransparentBlock(copyOf(PACKED_ICE).noOcclusion()));
		add("packed_icicle_block", () -> new BaseTransparentBlock(copyOf(PACKED_ICE).noOcclusion()));
		add("icicle_pane", () -> new BasePaneBlock(copyOf(PACKED_ICE).noOcclusion()));
		add("icicle_bars", () -> new BasePaneBlock(copyOf(ICE).noOcclusion()));
		add("cryomarble_block", () -> new Block(copyOf(DIAMOND_BLOCK)));
		add("snowguy", () -> new SnowguyBlock(copyOf(WHITE_WOOL).sound(SoundType.SNOW)));
		add("snowball_wall", () -> new SnowballWallBlock(copyOf(WHITE_WOOL).sound(SoundType.SNOW).noOcclusion()));
		add("dense_snow", () -> new Block(copyOf(WHITE_WOOL).sound(SoundType.SNOW)));
		add("dense_snow_stairs", () -> new BaseStairsBlock(SNOW_BLOCK.defaultBlockState(), copyOf(WHITE_WOOL).sound(SoundType.SNOW)));
		add("dense_snow_slab", () -> new SlabBlock(copyOf(WHITE_WOOL).sound(SoundType.SNOW)));
		add("snow_bricks", () -> new Block(copyOf(WHITE_WOOL).sound(SoundType.SNOW)));
		add("snow_brick_stairs", () -> new BaseStairsBlock(SNOW_BLOCK.defaultBlockState(), copyOf(WHITE_WOOL).sound(SoundType.SNOW)));
		add("snow_brick_slab", () -> new SlabBlock(copyOf(WHITE_WOOL).sound(SoundType.SNOW)));
		add("raw_cryomarble_shard", () -> new IcicleBlock(copyOf(WHITE_WOOL).sound(SoundType.GLASS).lightLevel(state -> 12)));
		add("red_gift_box", () -> new GiftBoxBlock(copyOf(RED_WOOL).pushReaction(PushReaction.DESTROY)));
		add("orange_gift_box", () -> new GiftBoxBlock(copyOf(RED_WOOL).pushReaction(PushReaction.DESTROY)));
		add("yellow_gift_box", () -> new GiftBoxBlock(copyOf(RED_WOOL).pushReaction(PushReaction.DESTROY)));
		add("green_gift_box", () -> new GiftBoxBlock(copyOf(RED_WOOL).pushReaction(PushReaction.DESTROY)));
		add("cyan_gift_box", () -> new GiftBoxBlock(copyOf(RED_WOOL).pushReaction(PushReaction.DESTROY)));
		add("blue_gift_box", () -> new GiftBoxBlock(copyOf(RED_WOOL).pushReaction(PushReaction.DESTROY)));
		add("purple_gift_box", () -> new GiftBoxBlock(copyOf(RED_WOOL).pushReaction(PushReaction.DESTROY)));
		add("black_gift_box", () -> new GiftBoxBlock(copyOf(RED_WOOL).pushReaction(PushReaction.DESTROY)));
		add("white_gift_box", () -> new GiftBoxBlock(copyOf(RED_WOOL).pushReaction(PushReaction.DESTROY)));
		add("garland_lights", () -> new GarlandLightsBlock(copyOf(GREEN_WOOL).pushReaction(PushReaction.DESTROY).noCollission().sound(SoundType.CANDLE)));
		add("rainy_garland_lights", () -> new GarlandLightsBlock(copyOf(WHITE_WOOL).pushReaction(PushReaction.DESTROY).noCollission().sound(SoundType.CANDLE)));
	}

	public static void add(String name, Supplier<Block> block) {
		BLOCKS.put(Winterly.id(name), block);
	}

	public static BlockBehaviour.Properties copyOf(Block block) {
		return BlockBehaviour.Properties.ofFullCopy(block);
	}
}
