package io.github.rcneg.alexsmobsdelight.mixin;

import com.github.alexthe666.alexsmobs.entity.EntitySeagull;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import io.github.rcneg.alexsmobsdelight.accessor.IEntitySeagullData;
import io.github.rcneg.alexsmobsdelight.config.Config;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@Mixin(EntitySeagull.class)
public class EntitySeagullMixin implements IEntitySeagullData {

    @Unique
    private boolean amd$consumedEternalFood;
    @Unique
    private Map<MobEffect, MobEffectInstance> amd$consumedFoodEffects = Maps.newHashMap();

    @Override
    public boolean amd$getConsumedEternalFood() {
        return amd$consumedEternalFood;
    }

    @Override
    public void amd$setConsumedEternalFood(boolean flag) {
        amd$consumedEternalFood = flag;
    }

    @Override
    public Map<MobEffect, MobEffectInstance> amd$getEffects() {
        return amd$consumedFoodEffects;
    }

    @Override
    public void amd$setEffectMap(Map<MobEffect, MobEffectInstance> map) {
        amd$consumedFoodEffects = map;
    }

    @Inject(
            method = "eatItemEffect",
            at = @At("HEAD"),
            remap = false
    )
    private void amd$ApplyEternalTag(ItemStack heldItem, CallbackInfo ci) {
        if (heldItem != null){
            if(Config.ETERNAL_FOODS_ITEMS.contains(heldItem.getItem())){
                EntitySeagull seagull = (EntitySeagull) (Object) this;
                if(seagull instanceof IEntitySeagullData data){
                    data.amd$setConsumedEternalFood(true);
                }
            }
            FoodProperties food = heldItem.getItem().getFoodProperties(heldItem, null);
            if (food != null && !food.getEffects().isEmpty()) {
                List<Pair<MobEffectInstance, Float>> foodEffectsWithChance = food.getEffects();
                Map<MobEffect, MobEffectInstance> foodEffects = Maps.newHashMap();
                EntitySeagull seagull = (EntitySeagull) (Object) this;
                for(Pair<MobEffectInstance, Float> effectPair : foodEffectsWithChance){
                    if(seagull.getRandom().nextFloat() <= effectPair.getSecond()){
                        MobEffectInstance effectIns = effectPair.getFirst();
                        foodEffects.put(effectIns.getEffect(), effectIns);
                    }
                }
                if(seagull instanceof IEntitySeagullData data){
                    data.amd$setEffectMap(foodEffects);
                }
            }
        }
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void amd$save(CompoundTag tag, CallbackInfo ci) {
        tag.putBoolean("AmdConsumedEternalFood", this.amd$consumedEternalFood);
        if (!this.amd$consumedFoodEffects.isEmpty()) {
            ListTag listtag = new ListTag();
            for (MobEffectInstance mobeffectinstance : this.amd$consumedFoodEffects.values()) {
                listtag.add(mobeffectinstance.save(new CompoundTag()));
            }
            tag.put("AmdConsumedFoodEffects", listtag);
        }
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void amd$load(CompoundTag tag, CallbackInfo ci) {
        if (tag.contains("AmdConsumedEternalFood")){
            this.amd$consumedEternalFood = tag.getBoolean("AmdConsumedEternalFood");
        }
        if (tag.contains("AmdConsumedFoodEffects", 9)) {
            ListTag listtag = tag.getList("AmdConsumedFoodEffects", 10);

            for(int i = 0; i < listtag.size(); ++i) {
                CompoundTag compoundtag = listtag.getCompound(i);
                MobEffectInstance mobeffectinstance = MobEffectInstance.load(compoundtag);
                if (mobeffectinstance != null) {
                    this.amd$consumedFoodEffects.put(mobeffectinstance.getEffect(), mobeffectinstance);
                }
            }
        }
    }
}
