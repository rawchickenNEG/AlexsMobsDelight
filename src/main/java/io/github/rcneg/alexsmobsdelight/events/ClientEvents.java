package io.github.rcneg.alexsmobsdelight.events;

import io.github.rcneg.alexsmobsdelight.AlexsMobsDelight;
import io.github.rcneg.alexsmobsdelight.init.BlockRegistry;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = AlexsMobsDelight.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.HONEY_GLAZED_BEAR_MEAT_WITH_SALMON.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.MOOSE_SAUSAGE_WITH_SALMON.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.WILD_STEW.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.ALEXS_RICE_ROLL_MEDLEY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.STEAMED_STUFFED_CROCODILE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.LOBSTER_ROLL_MEDLEY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.ACACIA_BLOSSOM_BLOCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.BANANA_BLOCK.get(), RenderType.cutout());
    }
}
