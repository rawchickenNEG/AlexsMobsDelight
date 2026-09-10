package io.github.rcneg.alexsmobsdelight.items;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import vectorwing.farmersdelight.common.item.ConsumableItem;

public class MilkShakeItem extends ConsumableItem {
    public MilkShakeItem(Properties p_40682_) {
        super(p_40682_);
    }

    public MilkShakeItem(Properties p_40682_, boolean effectTooltip) {
        super(p_40682_, effectTooltip);
    }

    public int getUseDuration(ItemStack p_41360_) {
        return 40;
    }

    public UseAnim getUseAnimation(ItemStack p_41358_) {
        return UseAnim.DRINK;
    }

    public SoundEvent getDrinkingSound() {
        return SoundEvents.HONEY_DRINK;
    }

    public SoundEvent getEatingSound() {
        return SoundEvents.HONEY_DRINK;
    }
}
