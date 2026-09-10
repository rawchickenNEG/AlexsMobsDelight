package io.github.rcneg.alexsmobsdelight.events;

import io.github.rcneg.alexsmobsdelight.init.EffectRegistry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import vectorwing.farmersdelight.common.registry.ModEffects;

@Mod.EventBusSubscriber
public class EffectEvents {
    @SubscribeEvent
    public static void onApplyPotion(MobEffectEvent.Applicable event) {
        LivingEntity entity = event.getEntity();
        if(entity.hasEffect(EffectRegistry.NOURISH_SUPPLEMENT.get()) && entity.hasEffect(ModEffects.NOURISHMENT.get()) && event.getEffectInstance().getEffect() == ModEffects.NOURISHMENT.get()){
            if(entity.getPersistentData().getBoolean("AMDNourishSupplementAffected")){
                return;
            }
            entity.getPersistentData().putBoolean("AMDNourishSupplementAffected", true);
            int totalDuration = entity.getEffect(ModEffects.NOURISHMENT.get()).getDuration() + event.getEffectInstance().getDuration();
            entity.addEffect(new MobEffectInstance(ModEffects.NOURISHMENT.get(), totalDuration));
            entity.getPersistentData().putBoolean("AMDNourishSupplementAffected", false);
        }
    }
}
