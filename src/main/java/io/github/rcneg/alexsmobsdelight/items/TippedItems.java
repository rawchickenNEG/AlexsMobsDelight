package io.github.rcneg.alexsmobsdelight.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;

public class TippedItems extends Item {
    public TippedItems(Properties p_41383_) {
        super(p_41383_);
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flagIn)
    {
        super.appendHoverText(stack, context, tooltip, flagIn);
        tooltip.add(Component.translatable("tooltip.alexsmobsdelight." + stack.getItem()).withStyle(ChatFormatting.BLUE));
    }
}
