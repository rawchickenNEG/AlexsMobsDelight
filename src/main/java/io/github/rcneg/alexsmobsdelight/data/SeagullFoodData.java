package io.github.rcneg.alexsmobsdelight.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.neoforged.neoforge.common.util.INBTSerializable;

import java.util.HashMap;
import java.util.Map;

public final class SeagullFoodData implements INBTSerializable<CompoundTag> {
    private boolean consumedEternalFood;
    private final Map<MobEffect, MobEffectInstance> effects = new HashMap<>();

    public boolean isConsumedEternalFood() {
        return consumedEternalFood;
    }

    public void setConsumedEternalFood(boolean consumedEternalFood) {
        this.consumedEternalFood = consumedEternalFood;
    }

    public Map<MobEffect, MobEffectInstance> getEffects() {
        return effects;
    }

    public void setEffects(Map<MobEffect, MobEffectInstance> effects) {
        this.effects.clear();
        this.effects.putAll(effects);
    }

    @Override
    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("ConsumedEternalFood", consumedEternalFood);

        if (!effects.isEmpty()) {
            ListTag effectList = new ListTag();
            for (MobEffectInstance effect : effects.values()) {
                effectList.add(effect.save());
            }
            tag.put("ConsumedFoodEffects", effectList);
        }

        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag) {
        consumedEternalFood = tag.getBoolean("ConsumedEternalFood");
        effects.clear();

        if (tag.contains("ConsumedFoodEffects", 9)) {
            ListTag effectList = tag.getList("ConsumedFoodEffects", 10);
            for (int i = 0; i < effectList.size(); i++) {
                MobEffectInstance effect = MobEffectInstance.load(effectList.getCompound(i));
                if (effect != null) {
                    effects.put(effect.getEffect().value(), effect);
                }
            }
        }
    }
}
