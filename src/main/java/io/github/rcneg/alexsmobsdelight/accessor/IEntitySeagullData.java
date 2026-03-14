package io.github.rcneg.alexsmobsdelight.accessor;

import com.ibm.icu.impl.Pair;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.List;
import java.util.Map;

public interface IEntitySeagullData {
    boolean amd$getConsumedEternalFood();
    void amd$setConsumedEternalFood(boolean v);

    Map<MobEffect, MobEffectInstance> amd$getEffects();
    void amd$setEffectMap(Map<MobEffect, MobEffectInstance> map);
}
