package ru.pinkgoosik.winterly.mixin.common;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.pinkgoosik.winterly.Winterly;
import ru.pinkgoosik.winterly.extension.DecoratedMob;
import ru.pinkgoosik.winterly.util.HolidayUtils;

@Mixin(Skeleton.class)
public abstract class SkeletonExtension extends AbstractSkeleton implements DecoratedMob {
    private static final EntityDataAccessor<Boolean> winterly$DECORATED = SynchedEntityData.defineId(Skeleton.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> winterly$INDEX = SynchedEntityData.defineId(Skeleton.class, EntityDataSerializers.INT);

    protected SkeletonExtension(EntityType<? extends AbstractSkeleton> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    public boolean winterly$decorated() {
        return getEntityData().get(winterly$DECORATED);
    }

    @Override
    public int winterly$getIndex() {
        return getEntityData().get(winterly$INDEX);
    }

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    void initData(SynchedEntityData.Builder builder, CallbackInfo ci) {
		builder.define(winterly$DECORATED, false);
		builder.define(winterly$INDEX, 0);
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    void write(CompoundTag nbt, CallbackInfo ci) {
        nbt.putBoolean("WinterlyDecorated", getEntityData().get(winterly$DECORATED));
        nbt.putInt("WinterlyIndex", getEntityData().get(winterly$INDEX));
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    void read(CompoundTag nbt, CallbackInfo ci) {
        getEntityData().set(winterly$DECORATED, nbt.getBoolean("WinterlyDecorated"));
        getEntityData().set(winterly$INDEX, nbt.getInt("WinterlyIndex"));
    }

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
		spawnGroupData = super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);

		if(!spawnType.equals(MobSpawnType.SPAWNER) && !spawnType.equals(MobSpawnType.CHUNK_GENERATION)) {
			if(Winterly.config.mobDecorations.enabled && HolidayUtils.isWinterHolidays() || !Winterly.config.mobDecorations.onlyInWinter) {
				if(!this.level().dimension().equals(Level.NETHER)) {
					int chance = Winterly.config.mobDecorations.chance;
					if(chance > 0 && Math.random() < (double)chance / 100) {
						getEntityData().set(winterly$DECORATED, true);
						getEntityData().set(winterly$INDEX, level.getRandom().nextInt(5));
					}
				}
			}
		}
		return spawnGroupData;
	}

}
