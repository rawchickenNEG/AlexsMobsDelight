package io.github.rcneg.alexsmobsdelight.items;

import com.alexsmobsup.effect.AMEffectRegistry;
import io.github.rcneg.alexsmobsdelight.init.EffectRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import vectorwing.farmersdelight.common.item.ConsumableItem;

import java.util.List;

public class FlyingFishCanOpened extends ConsumableItem {

    public FlyingFishCanOpened(Item.Properties properties) {
        super(properties);
    }

    public FlyingFishCanOpened(Item.Properties properties, boolean hasFoodEffectTooltip) {
        super(properties, hasFoodEffectTooltip);
    }

    public SoundEvent getEatingSound() {
        return SoundEvents.HONEY_DRINK;
    }

    @Override
    public void inventoryTick(ItemStack itemstack, Level level, Entity entity, int slot, boolean selected) {
        super.inventoryTick(itemstack, level, entity, slot, selected);
        if(level.getGameTime() % 20L == 0L){
            for (LivingEntity nearby : level.getEntitiesOfClass(LivingEntity.class, entity.getBoundingBox().inflate(16.0))) {
                nearby.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 80, 0));
            }
            if(entity instanceof LivingEntity living){
                living.addEffect(new MobEffectInstance(EffectRegistry.SEAGULL_ANOREXIA, 80, 0));
                living.addEffect(new MobEffectInstance(AMEffectRegistry.MOSQUITO_REPELLENT, 80, 0));
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flagIn)
    {
        super.appendHoverText(stack, context, tooltip, flagIn);
        tooltip.add(Component.translatable("tooltip.alexsmobsdelight.surflygfisk_open").withStyle(ChatFormatting.DARK_AQUA));
    }
}
