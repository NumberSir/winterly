package ru.pinkgoosik.winterly.item.tool;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class CryomarbleHoeItem extends HoeItem {

	public CryomarbleHoeItem(Tier tier, Properties properties) {
		super(tier, properties);
	}

	@Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 0));
        return super.hurtEnemy(stack, target, attacker);
    }
}
