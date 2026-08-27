package io.github.rcneg.alexsmobsdelight.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;

public class MantisShrimpShovel extends ShovelItem {
    public MantisShrimpShovel(Tier p_43114_, float p_43115_, float p_43116_, Properties p_43117_) {
        super(p_43114_, p_43117_.attributes(DiggerItem.createAttributes(p_43114_, p_43115_, p_43116_)));
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flagIn)
    {
        super.appendHoverText(stack, context, tooltip, flagIn);
        tooltip.add(Component.translatable("tooltip.alexsmobsdelight.mantis_shrimp_tools").withStyle(ChatFormatting.GOLD));
    }
}
