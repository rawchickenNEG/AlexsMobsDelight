package io.github.rcneg.alexsmobsdelight.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;

public class MantisShrimpAxe extends AxeItem
{
    public MantisShrimpAxe(Tier p_40521_, float p_40522_, float p_40523_, Properties p_40524_) {
        super(p_40521_, p_40524_.attributes(DiggerItem.createAttributes(p_40521_, p_40522_, p_40523_)));
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flagIn)
    {
        super.appendHoverText(stack, context, tooltip, flagIn);
        tooltip.add(Component.translatable("tooltip.alexsmobsdelight.mantis_shrimp_tools").withStyle(ChatFormatting.GOLD));
    }
}
