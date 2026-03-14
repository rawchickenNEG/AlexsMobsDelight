package io.github.rcneg.alexsmobsdelight.effects;

import io.github.rcneg.alexsmobsdelight.init.EffectRegistry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class CrocodileSharpnessEffect extends MobEffect {
    public CrocodileSharpnessEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if(entity.hasEffect(EffectRegistry.CROCODILE_CRUSH.get())
                && entity.hasEffect(EffectRegistry.CROCODILE_TOUGHNESS.get())
                && entity.hasEffect(EffectRegistry.CROCODILE_HACKSAW.get())){
            entity.addEffect(new MobEffectInstance(EffectRegistry.CROCODILE_DEATH_ROLL.get(), 1800, amplifier));
            entity.removeEffect(this);
        }
    }

    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration > 0;
    }
}
