package io.github.rcneg.alexsmobsdelight.items;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import vectorwing.farmersdelight.common.item.ConsumableItem;

public class CheeseItem extends ConsumableItem {

    public CheeseItem(Properties properties) {
        super(properties);
    }

    public CheeseItem(Properties properties, boolean hasFoodEffectTooltip) {
        super(properties, hasFoodEffectTooltip);
    }

    public SoundEvent getEatingSound() {
        return SoundEvents.HONEY_DRINK;
    }
}
