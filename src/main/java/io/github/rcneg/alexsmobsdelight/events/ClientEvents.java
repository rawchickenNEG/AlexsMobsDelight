package io.github.rcneg.alexsmobsdelight.events;

import io.github.rcneg.alexsmobsdelight.AlexsMobsDelight;
import io.github.rcneg.alexsmobsdelight.client.model.CustomItemBakedModel;
import io.github.rcneg.alexsmobsdelight.init.BlockRegistry;
import io.github.rcneg.alexsmobsdelight.init.ItemRegistry;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

@EventBusSubscriber(modid = AlexsMobsDelight.MODID, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        for(DeferredHolder<Block, ? extends Block> blocks: BlockRegistry.BLOCKS.getEntries()){
            ItemBlockRenderTypes.setRenderLayer(blocks.get(), RenderType.cutout());
        }
    }

    @SubscribeEvent
    public static void onModelBake(ModelEvent.ModifyBakingResult event) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(AlexsMobsDelight.MODID, BuiltInRegistries.ITEM.getKey(ItemRegistry.DIMENSIONAL_FOOD.get()).getPath());
        ModelResourceLocation location = new ModelResourceLocation(id, "inventory");

        BakedModel original = event.getModels().get(location);
        if (original != null) {
            event.getModels().put(location, new CustomItemBakedModel(original));
        }
    }
}
