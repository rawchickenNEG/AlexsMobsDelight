package io.github.rcneg.alexsmobsdelight.init;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(value = Dist.CLIENT)
public class PropertyRegistry {
    @SubscribeEvent
    public static void propertyOverrideRegistry(FMLClientSetupEvent event) {
        ItemProperties.register(ItemRegistry.BANANA_BOW.get(), ResourceLocation.withDefaultNamespace("pull"), (p_174635_, p_174636_, p_174637_, p_174638_) -> {
            if (p_174637_ == null) {
                return 0.0F;
            } else {
                return p_174637_.getUseItem() != p_174635_ ? 0.0F : (float) (p_174635_.getUseDuration(p_174637_) - p_174637_.getUseItemRemainingTicks()) / 20.0F;
            }
        });
        ItemProperties.register(ItemRegistry.BANANA_BOW.get(), ResourceLocation.withDefaultNamespace("pulling"), (p_174630_, p_174631_, p_174632_, p_174633_) ->
                p_174632_ != null && p_174632_.isUsingItem() && p_174632_.getUseItem() == p_174630_ ? 1.0F : 0.0F);
    }
}
