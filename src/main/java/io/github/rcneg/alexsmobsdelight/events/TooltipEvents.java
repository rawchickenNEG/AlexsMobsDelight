package io.github.rcneg.alexsmobsdelight.events;

import com.alexsmobsup.item.AMItemRegistry;
import io.github.rcneg.alexsmobsdelight.AlexsMobsDelight;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@EventBusSubscriber(modid = AlexsMobsDelight.MODID, value = {Dist.CLIENT})
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