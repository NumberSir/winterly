package ru.pinkgoosik.winterly.neoforge.data;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.jetbrains.annotations.Nullable;
import ru.pinkgoosik.winterly.Winterly;
import ru.pinkgoosik.winterly.data.CachedFlowers;

public class WinterlyDataAttachments {
    private static AttachmentType<ChunkData> CHUNK_DATA;

    public static void init(RegisterEvent.RegisterHelper<AttachmentType<?>> registry) {
		CHUNK_DATA = AttachmentType.builder(ChunkData::new).serialize(new ChunkData.ChunkDataIAttachmentSerializer()).build();

		registry.register(Winterly.id("chunk_data"), CHUNK_DATA);

        CachedFlowers.instance = new CachedFlowers() {
            @Override
            public @Nullable Block getFlowerImpl(Level world, BlockPos pos) {
                return world.getChunkAt(pos).getData(CHUNK_DATA).cachedFlowers.get(pos);
            }

            @Override
            public void cacheFlowerImpl(Level world, BlockPos pos, Block flower) {
                world.getChunkAt(pos).getData(CHUNK_DATA).cachedFlowers.put(pos, flower);
            }
        };
    }
}
