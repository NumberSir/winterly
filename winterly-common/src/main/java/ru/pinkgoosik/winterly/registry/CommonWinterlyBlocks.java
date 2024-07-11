package ru.pinkgoosik.winterly.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import ru.pinkgoosik.winterly.Winterly;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import ru.pinkgoosik.winterly.Winterly;
import ru.pinkgoosik.winterly.block.*;
import ru.pinkgoosik.winterly.block.base.BasePaneBlock;
import ru.pinkgoosik.winterly.block.base.BaseStairsBlock;
import ru.pinkgoosik.winterly.block.base.BaseTransparentBlock;

import static net.minecraft.world.level.block.Blocks.*;
import static ru.pinkgoosik.winterly.registry.CommonWinterlyBlocks.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public abstract class CommonWinterlyBlocks {
//    public static final Map<ResourceLocation, Supplier<BlockItem>> ITEMS = new LinkedHashMap<>();
    public static final Map<ResourceLocation, Supplier<Block>> BLOCKS = new LinkedHashMap<>();

//    public static final Block RED_SOCK = add("red_sock", new SockBlock(copyOf(CANDLE).sounds(BlockSoundGroup.WOOL)));
//    public static final Block GREEN_SOCK = add("green_sock", new SockBlock(copyOf(CANDLE).sounds(BlockSoundGroup.WOOL)));
//    public static final Block BLUE_SOCK = add("blue_sock", new SockBlock(copyOf(CANDLE).sounds(BlockSoundGroup.WOOL)));
//
//    public static Block ICICLE = add("icicle", new IcicleBlock(copyOf(ICE).pushReaction(PushReaction.DESTROY)));
//    public static Block ICICLE_BLOCK = add("icicle_block", new BaseTransparentBlock(copyOf(PACKED_ICE).noOcclusion()));
//	public static Block PACKED_ICICLE_BLOCK = add("packed_icicle_block", new BaseTransparentBlock(copyOf(PACKED_ICE).noOcclusion()));
//    public static Block ICICLE_PANE = add("icicle_pane", new BasePaneBlock(copyOf(PACKED_ICE).noOcclusion()));
//    public static Block ICICLE_BARS = add("icicle_bars", new BasePaneBlock(copyOf(ICE).noOcclusion()));
//	public static Block CRYOMARBLE_BLOCK = add("cryomarble_block", new Block(copyOf(DIAMOND_BLOCK)));
//    public static Block SNOWGUY = add("snowguy", new SnowguyBlock(copyOf(WHITE_WOOL).sound(SoundType.SNOW)));
//    public static Block SNOWBALL_WALL = add("snowball_wall", new SnowballWallBlock(copyOf(WHITE_WOOL).sound(SoundType.SNOW).noOcclusion()));
//    public static Block DENSE_SNOW = add("dense_snow", new Block(copyOf(WHITE_WOOL).sound(SoundType.SNOW)));
//    public static Block DENSE_SNOW_STAIRS = add("dense_snow_stairs", new BaseStairsBlock(SNOW_BLOCK.defaultBlockState(), copyOf(WHITE_WOOL).sound(SoundType.SNOW)));
//    public static Block DENSE_SNOW_SLAB = add("dense_snow_slab", new SlabBlock(copyOf(WHITE_WOOL).sound(SoundType.SNOW)));
//    public static Block SNOW_BRICKS = add("snow_bricks", new Block(copyOf(WHITE_WOOL).sound(SoundType.SNOW)));
//    public static Block SNOW_BRICK_STAIRS = add("snow_brick_stairs", new BaseStairsBlock(SNOW_BLOCK.defaultBlockState(), copyOf(WHITE_WOOL).sound(SoundType.SNOW)));
//    public static Block SNOW_BRICK_SLAB = add("snow_brick_slab", new SlabBlock(copyOf(WHITE_WOOL).sound(SoundType.SNOW)));
//	public static Block FROZEN_GRASS;
//	public static Block FROZEN_FLOWER;
//    public static Block RAW_CRYOMARBLE_SHARD = add("raw_cryomarble_shard", new IcicleBlock(copyOf(WHITE_WOOL).sound(SoundType.GLASS).lightLevel(state -> 12)));
//	public static Block RED_GIFT_BOX = add("red_gift_box", new GiftBoxBlock(copyOf(RED_WOOL).pushReaction(PushReaction.DESTROY)));
//	public static Block ORANGE_GIFT_BOX = add("orange_gift_box", new GiftBoxBlock(copyOf(RED_GIFT_BOX)));
//	public static Block YELLOW_GIFT_BOX = add("yellow_gift_box", new GiftBoxBlock(copyOf(RED_GIFT_BOX)));
//	public static Block GREEN_GIFT_BOX = add("green_gift_box", new GiftBoxBlock(copyOf(RED_GIFT_BOX)));
//	public static Block CYAN_GIFT_BOX = add("cyan_gift_box", new GiftBoxBlock(copyOf(RED_GIFT_BOX)));
//	public static Block BLUE_GIFT_BOX = add("blue_gift_box", new GiftBoxBlock(copyOf(RED_GIFT_BOX)));
//	public static Block PURPLE_GIFT_BOX = add("purple_gift_box", new GiftBoxBlock(copyOf(RED_GIFT_BOX)));
//	public static Block BLACK_GIFT_BOX = add("black_gift_box", new GiftBoxBlock(copyOf(RED_GIFT_BOX)));
//	public static Block WHITE_GIFT_BOX = add("white_gift_box", new GiftBoxBlock(copyOf(RED_GIFT_BOX)));
//    public static Block GARLAND_LIGHTS = add("garland_lights", new GarlandLightsBlock(copyOf(GREEN_WOOL).pushReaction(PushReaction.DESTROY).noCollission().sound(SoundType.CANDLE)));
//    public static Block RAINY_GARLAND_LIGHTS = add("rainy_garland_lights", new GarlandLightsBlock(copyOf(WHITE_WOOL).pushReaction(PushReaction.DESTROY).noCollission().sound(SoundType.CANDLE)));

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

//	public static <T extends Block> T addBlockItem(String name, T block, BlockItem item) {
//		addBlock(name, block);
//		if (item != null) {
//			item.registerBlocks(Item.BY_BLOCK, item);
//			ITEMS.put(Winterly.id(name), item);
//		}
//		return block;
//	}
//
//	public static <T extends Block> T addBlock(String name, T block) {
//		BLOCKS.put(Winterly.id(name), block);
//		return block;
//	}

	public static BlockBehaviour.Properties copyOf(Block block) {
		return BlockBehaviour.Properties.ofFullCopy(block);
	}
}
