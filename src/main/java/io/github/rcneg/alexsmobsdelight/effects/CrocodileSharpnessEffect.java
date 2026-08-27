package io.github.rcneg.alexsmobsdelight.effects;

import io.github.rcneg.alexsmobsdelight.init.EffectRegistry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class CrocodileSharpnessEffect extends MobEffect {
    public CrocodileSharpnessEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if(entity.hasEffect(EffectRegistry.CROCODILE_CRUSH)
                && entity.hasEffect(EffectRegistry.CROCODILE_TOUGHNESS)
                && entity.hasEffect(EffectRegistry.CROCODILE_HACKSAW)){
            entity.addEffect(new MobEffectInstance(EffectRegistry.CROCODILE_DEATH_ROLL, 1800, amplifier));
            entity.removeEffect(EffectRegistry.CROCODILE_SHARPNESS);
        }
        return true;
    }

    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration > 0;
    }
}
