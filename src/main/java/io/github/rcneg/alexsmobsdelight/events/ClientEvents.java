package io.github.rcneg.alexsmobsdelight.events;

import com.github.alexthe666.alexsmobs.ClientProxy;
import com.github.alexthe666.alexsmobs.client.ClientLayerRegistry;
import com.github.alexthe666.alexsmobs.client.render.item.GhostlyPickaxeBakedModel;
import io.github.rcneg.alexsmobsdelight.AlexsMobsDelight;
import io.github.rcneg.alexsmobsdelight.client.model.CustomItemBakedModel;
import io.github.rcneg.alexsmobsdelight.init.BlockRegistry;
import io.github.rcneg.alexsmobsdelight.init.ItemRegistry;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = AlexsMobsDelight.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        for(RegistryObject<Block> blocks: BlockRegistry.BLOCKS.getEntries()){
            ItemBlockRenderTypes.setRenderLayer(blocks.get(), RenderType.cutout());
        }
    }

    @SubscribeEvent
    public static void onModelBake(ModelEvent.ModifyBakingResult event) {
        ResourceLocation id = new ResourceLocation(AlexsMobsDelight.MODID, ForgeRegistries.ITEMS.getKey(ItemRegistry.DIMENSIONAL_FOOD.get()).getPath());
        ModelResourceLocation location = new ModelResourceLocation(id, "inventory");

        BakedModel original = event.getModels().get(location);
        if (original != null) {
            event.getModels().put(location, new CustomItemBakedModel(original));
        }
    }

    @SubscribeEvent
    public static void onModifyBakingResult(ModelEvent.ModifyBakingResult event) {
        ModelResourceLocation location = new ModelResourceLocation(new ResourceLocation(AlexsMobsDelight.MODID, "ghostly_burger"), "inventory");
        BakedModel originalModel = event.getModels().get(location);
        if (originalModel != null) {
            event.getModels().put(location, new GhostlyPickaxeBakedModel(originalModel));
        }
    }

}
