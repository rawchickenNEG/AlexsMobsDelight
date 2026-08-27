package io.github.rcneg.alexsmobsdelight.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import vectorwing.farmersdelight.common.item.ConsumableItem;

import javax.annotation.Nullable;

public class SlowEatConsumableItem extends ConsumableItem {

    public SlowEatConsumableItem(Item.Properties properties) {
        super(properties);
    }

    public SlowEatConsumableItem(Item.Properties properties, boolean hasFoodEffectTooltip) {
        super(properties, hasFoodEffectTooltip);
    }

    public SlowEatConsumableItem(Item.Properties properties, boolean hasFoodEffectTooltip, boolean hasCustomTooltip) {
        super(properties, hasFoodEffectTooltip, hasCustomTooltip);
    }

    @Override
    public int getUseDuration(ItemStack p_41454_, @Nullable LivingEntity entity) {
        if (p_41454_.getFoodProperties(entity) != null) {
            return 64;
        }
        return 0;
    }
}
