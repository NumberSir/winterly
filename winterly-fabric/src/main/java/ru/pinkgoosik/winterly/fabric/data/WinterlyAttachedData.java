package ru.pinkgoosik.winterly.fabric.data;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;
import ru.pinkgoosik.winterly.Winterly;
import ru.pinkgoosik.winterly.data.CachedFlowers;

import java.util.LinkedHashMap;
import java.util.Map;

public class WinterlyAttachedData {
	public static final AttachmentType<Map<String, String>> CACHED_FLOWERS = AttachmentRegistry.createPersistent(Winterly.id("cached_flowers"), Codec.unboundedMap(Codec.STRING, Codec.STRING));

	public static void init() {
		ServerChunkEvents.CHUNK_LOAD.register((world, chunk) -> {
			var map = chunk.getAttached(CACHED_FLOWERS);

			if(map != null) {
				if(map instanceof ImmutableMap<String, String>) {
					chunk.setAttached(CACHED_FLOWERS, Maps.newLinkedHashMap(map));
				}
			}
		});

		CachedFlowers.instance = new CachedFlowers() {
			@Override
			public @Nullable Block getFlowerImpl(Level world, BlockPos pos) {
				var map = world.getChunkAt(pos).getAttached(CACHED_FLOWERS);
				if(map != null) {
					var block = map.get(pos.toShortString());
					if(block != null) return BuiltInRegistries.BLOCK.get(ResourceLocation.parse(block));
				}
				else {
					world.getChunkAt(pos).setAttached(CACHED_FLOWERS, new LinkedHashMap<String, String>());
				}
				return null;
			}

			@Override
			public void cacheFlowerImpl(Level world, BlockPos pos, Block flower) {
				Map<String, String> map = world.getChunkAt(pos).getAttached(CACHED_FLOWERS);

				if(map != null) {
					map.put(pos.toShortString(), BuiltInRegistries.BLOCK.getKey(flower).toString());
				}
				else {
					map = new LinkedHashMap<>();
					map.put(pos.toShortString(), BuiltInRegistries.BLOCK.getKey(flower).toString());
					world.getChunkAt(pos).setAttached(CACHED_FLOWERS, map);
				}

			}
		};
	}
}
