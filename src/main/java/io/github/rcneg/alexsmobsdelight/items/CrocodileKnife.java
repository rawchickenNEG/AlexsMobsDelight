package io.github.rcneg.alexsmobsdelight.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import vectorwing.farmersdelight.common.item.KnifeItem;

import java.util.List;

public class CrocodileKnife extends KnifeItem {
    public CrocodileKnife(Tier tier, float attackDamage, float attackSpeed, Properties properties) {
        super(tier, properties.attributes(DiggerItem.createAttributes(tier, attackDamage, attackSpeed)));
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flagIn)
    {
        super.appendHoverText(stack, context, tooltip, flagIn);
        tooltip.add(Component.translatable("tooltip.alexsmobsdelight.crocodile_knife").withStyle(ChatFormatting.GREEN));
    }
}
