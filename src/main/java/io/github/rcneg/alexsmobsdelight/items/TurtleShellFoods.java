package io.github.rcneg.alexsmobsdelight.items;

import io.github.rcneg.alexsmobsdelight.init.ItemRegistry;
import net.minecraft.world.item.ItemStack;
import vectorwing.farmersdelight.common.item.ConsumableItem;

public class TurtleShellFoods extends ConsumableItem {
    public TurtleShellFoods(Properties properties) {
        super(properties);
    }

    public TurtleShellFoods(Properties properties, boolean hasFoodEffectTooltip) {
        super(properties, hasFoodEffectTooltip);
    }

    public TurtleShellFoods(Properties properties, boolean hasFoodEffectTooltip, boolean hasCustomTooltip) {
        super(properties, hasFoodEffectTooltip, hasCustomTooltip);
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack stack) {
        return new ItemStack(ItemRegistry.TERRAPIN_SHELL.get());
    }
}
