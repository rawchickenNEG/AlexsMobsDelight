package io.github.rcneg.alexsmobsdelight.items;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.item.ConsumableItem;

import java.util.ArrayList;
import java.util.List;

public class CheeseItem extends ConsumableItem {

    public CheeseItem(Properties properties) {
        super(properties);
    }

    public CheeseItem(Properties properties, boolean hasFoodEffectTooltip) {
        super(properties, hasFoodEffectTooltip);
    }

    public CheeseItem(Properties properties, boolean hasFoodEffectTooltip, boolean hasCustomTooltip) {
        super(properties, hasFoodEffectTooltip, hasCustomTooltip);
    }

    public ItemStack finishUsingItem(ItemStack st, Level level, LivingEntity e) {
        List<MobEffectInstance> list = new ArrayList<>(e.getActiveEffects());
        for (MobEffectInstance ins : list) {
            if (ins.getEffect().getCategory() == MobEffectCategory.BENEFICIAL || ins.getEffect().getCategory() == MobEffectCategory.NEUTRAL)
                continue;
            e.removeEffect(ins.getEffect());
        }
        return super.finishUsingItem(st, level, e);
    }
}
