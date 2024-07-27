package net.raia.starcana.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class StarSearcher extends StatusEffect {

    public StarSearcher() {
        super(StatusEffectCategory.BENEFICIAL, 0xAC009F);


    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(!entity.getWorld().isClient() && entity.getWorld().isDay())
        {
            entity.removeStatusEffect(this);
        }
    }
}
