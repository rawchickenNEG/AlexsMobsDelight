package io.github.rcneg.alexsmobsdelight.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;

public class MantisShrimpHammer extends PickaxeItem {
    public MantisShrimpHammer(Tier p_42961_, int p_42962_, float p_42963_, Properties p_42964_) {
        super(p_42961_, p_42964_.attributes(DiggerItem.createAttributes(p_42961_, p_42962_, p_42963_)));
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flagIn)
    {
        super.appendHoverText(stack, context, tooltip, flagIn);
        tooltip.add(Component.translatable("tooltip.alexsmobsdelight.mantis_shrimp_tools").withStyle(ChatFormatting.GOLD));
    }
}
