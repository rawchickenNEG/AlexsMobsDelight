package io.github.rcneg.alexsmobsdelight.events;

import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import io.github.rcneg.alexsmobsdelight.AlexsMobsDelight;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AlexsMobsDelight.MODID, value = {Dist.CLIENT})
public class TooltipEvents {
    public TooltipEvents() {
    }
    @SubscribeEvent
    public static void addTooltipPlantableFoods(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.is(AMItemRegistry.BANANA.get())) {
            event.getToolTip().add(Component.translatable("tooltip.alexsmobsdelight.banana").withStyle(ChatFormatting.BLUE));
        }
        if (stack.is(AMItemRegistry.ACACIA_BLOSSOM.get())) {
            event.getToolTip().add(Component.translatable("tooltip.alexsmobsdelight.acacia_blossom").withStyle(ChatFormatting.BLUE));
        }
    }
}