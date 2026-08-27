package io.github.rcneg.alexsmobsdelight.init;

import com.alexsmobsup.client.render.RenderCrimsonMosquito;
import io.github.rcneg.alexsmobsdelight.client.renderer.SuperSeagullRenderer;
import io.github.rcneg.alexsmobsdelight.client.renderer.ThrownDartRenderer;
import io.github.rcneg.alexsmobsdelight.client.renderer.ThrownPointedItemRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(value = Dist.CLIENT)
public class RendererRegistry {

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_DART.get(), ThrownDartRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.THROWN_BANANA.get(), ThrownPointedItemRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.SUPER_SEAGULL.get(), SuperSeagullRenderer::new);
        event.registerEntityRenderer(EntityTypeRegistry.SUPER_MOSQUITO.get(), RenderCrimsonMosquito::new);

    }

}