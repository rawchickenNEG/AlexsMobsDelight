package io.github.rcneg.alexsmobsdelight.events;

import com.alexsmobsup.entity.EntitySeagull;
import com.google.common.collect.Maps;
import io.github.rcneg.alexsmobsdelight.AlexsMobsDelight;
import io.github.rcneg.alexsmobsdelight.config.Config;
import io.github.rcneg.alexsmobsdelight.data.SeagullFoodData;
import io.github.rcneg.alexsmobsdelight.helper.ItemStackDataCompat;
import io.github.rcneg.alexsmobsdelight.init.AttachmentRegistry;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.VanillaGameEvent;

import java.util.Map;
import java.util.Set;

@EventBusSubscriber(modid = AlexsMobsDelight.MODID)
public final class SeagullEvents {
    private SeagullEvents() {
    }

    @SubscribeEvent
    public static void onSeagullEat(VanillaGameEvent event) {
        if (!isEatEvent(event) || !(event.getCause() instanceof EntitySeagull seagull)) {
            return;
        }

        ItemStack heldItem = seagull.getMainHandItem();
        SeagullFoodData data = seagull.getData(AttachmentRegistry.SEAGULL_FOOD_DATA);

        if (Config.ETERNAL_FOODS_ITEMS.contains(heldItem.getItem())) {
            data.setConsumedEternalFood(true);
        }

        FoodProperties food = heldItem.getItem().getFoodProperties(heldItem, null);
        if (food == null) {
            return;
        }

        Map<MobEffect, MobEffectInstance> foodEffects = Maps.newHashMap();
        if (!food.effects().isEmpty() && !Config.ENCHANTED_SEAGULL_BLACKLIST_ITEMS.contains(heldItem.getItem())) {
            for (FoodProperties.PossibleEffect effectPair : food.effects()) {
                if (seagull.getRandom().nextFloat() <= effectPair.probability()) {
                    MobEffectInstance effect = effectPair.effect();
                    foodEffects.put(effect.getEffect().value(), effect);
                }
            }
        }

        CompoundTag heldItemData = ItemStackDataCompat.read(heldItem);
        if (!heldItemData.isEmpty()) {
            Set<String> tagNames = heldItemData.getAllKeys();
            for (String tagName : tagNames) {
                ListTag effectList = heldItemData.getList(tagName, 10);
                for (int i = 0; i < effectList.size(); i++) {
                    MobEffectInstance effect = MobEffectInstance.load(effectList.getCompound(i));
                    if (effect == null) {
                        continue;
                    }

                    MobEffect mobEffect = effect.getEffect().value();
                    MobEffectInstance existing = foodEffects.get(mobEffect);
                    if (existing != null) {
                        effect = new MobEffectInstance(
                                effect.getEffect(),
                                Math.max(effect.getDuration(), existing.getDuration()),
                                Math.max(effect.getAmplifier(), existing.getAmplifier())
                        );
                    }
                    foodEffects.put(mobEffect, effect);
                }
            }
        }

        data.setEffects(foodEffects);
    }

    private static boolean isEatEvent(VanillaGameEvent event) {
        Holder<GameEvent> vanillaEvent = event.getVanillaEvent();
        return vanillaEvent.is(GameEvent.EAT);
    }
}
