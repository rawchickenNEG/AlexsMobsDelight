package io.github.rcneg.alexsmobsdelight.items;

import io.github.rcneg.alexsmobsdelight.config.Config;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EternalFoodItem extends Item {
    public EternalFoodItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        if (isEdible()) {
            entity.eat(world, stack.copy());
            if(entity instanceof Player player){
                player.getCooldowns().addCooldown(stack.getItem(), Config.ETERNAL_SEAGULL_COOLDOWN.get());
            }
        }
        return stack;
    }
}
