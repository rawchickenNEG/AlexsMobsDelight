package io.github.rcneg.alexsmobsdelight.items;

import io.github.rcneg.alexsmobsdelight.config.Config;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CompassItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class EnchantedEternalSeagullItem extends Item {
    public EnchantedEternalSeagullItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        if (isEdible()) {
            CompoundTag tag = stack.getOrCreateTag();
            if((tag.contains("AmdConsumedFoodEffects", 9))){
                ListTag listtag = tag.getList("AmdConsumedFoodEffects", 10);

                for(int i = 0; i < listtag.size(); ++i) {
                    CompoundTag compoundtag = listtag.getCompound(i);
                    MobEffectInstance mobeffectinstance = MobEffectInstance.load(compoundtag);
                    if (mobeffectinstance != null) {
                        entity.addEffect(mobeffectinstance);
                    }
                }
            }

            entity.eat(world, stack.copy());
            if(entity instanceof Player player){
                player.getCooldowns().addCooldown(stack.getItem(), Config.ETERNAL_SEAGULL_COOLDOWN.get());
            }
        }

        return stack;
    }

    @Override
    public boolean isFoil(ItemStack p_41453_) {
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
    {
        CompoundTag tag = stack.getOrCreateTag();
        if((tag.contains("AmdConsumedFoodEffects", 9))){
            ListTag listtag = tag.getList("AmdConsumedFoodEffects", 10);

            for(int i = 0; i < listtag.size(); ++i) {
                CompoundTag compoundtag = listtag.getCompound(i);
                MobEffectInstance mobeffectinstance = MobEffectInstance.load(compoundtag);
                if (mobeffectinstance != null) {
                    String effectLevel = mobeffectinstance.getAmplifier() != 0 ? " " + Component.translatable("enchantment.level." + (mobeffectinstance.getAmplifier() + 1)).getString() : "";
                    MobEffect effect = mobeffectinstance.getEffect();
                    ChatFormatting format = effect.getCategory() == MobEffectCategory.BENEFICIAL ? ChatFormatting.BLUE : effect.getCategory() == MobEffectCategory.HARMFUL ? ChatFormatting.RED : ChatFormatting.GRAY;
                    tooltip.add(Component.translatable("tooltip.alexsmobsdelight.effect_desc", effect.getDisplayName().getString(), effectLevel, MobEffectUtil.formatDuration(mobeffectinstance, 1.0f)).withStyle(format));
                }
            }
        }
    }
}
