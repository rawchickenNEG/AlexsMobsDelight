package io.github.rcneg.alexsmobsdelight.items;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import vectorwing.farmersdelight.common.item.ConsumableItem;

public class KiviakItem extends ConsumableItem {

    public KiviakItem(Properties properties) {
        super(properties);
    }

    public KiviakItem(Properties properties, boolean hasFoodEffectTooltip) {
        super(properties, hasFoodEffectTooltip);
    }

    public SoundEvent getEatingSound() {
        return SoundEvents.HONEY_DRINK;
    }
}
