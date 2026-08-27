package io.github.rcneg.alexsmobsdelight;

import io.github.rcneg.alexsmobsdelight.blocks.MaggotFarmBlock;
import io.github.rcneg.alexsmobsdelight.config.Config;
import io.github.rcneg.alexsmobsdelight.init.*;
import net.minecraft.core.component.DataComponents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;

@Mod(AlexsMobsDelight.MODID)
public class AlexsMobsDelight
{
    public static final String MODID = "alexsmobsdelight";

    public AlexsMobsDelight(IEventBus modEventBus, ModContainer modContainer)
    {
        CriticalTriggerRegistry.init();
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::modifyDefaultComponents);
        ItemRegistry.ITEMS.register(modEventBus);
        BlockRegistry.BLOCKS.register(modEventBus);
        RecipeRegistry.DEF_REG.register(modEventBus);
        EffectRegistry.MOB_EFFECTS.register(modEventBus);
        EntityTypeRegistry.ENTITY_TYPES.register(modEventBus);
        LootModifierRegistry.LOOT_MODIFIER.register(modEventBus);
        TabRegistry.CREATIVE_MODE_TABS.register(modEventBus);
        modEventBus.addListener(NetworkRegistry::register);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            MaggotFarmBlock.bootStrap();
        });
    }

    private void modifyDefaultComponents(ModifyDefaultComponentsEvent event) {
        // Datagen fires this event before NeoForge has loaded the common config.  Use
        // the declared default in that phase, while retaining the configured value
        // during a normal client/server launch.
        boolean stackableSoupItems;
        try {
            stackableSoupItems = Config.STACKABLE_SOUP_ITEMS.get();
        } catch (IllegalStateException ignored) {
            stackableSoupItems = Config.STACKABLE_SOUP_ITEMS.getDefault();
        }
        if (!stackableSoupItems) return;
        event.modify(com.alexsmobsup.item.AMItemRegistry.MOSQUITO_REPELLENT_STEW.get(),
                patch -> patch.set(DataComponents.MAX_STACK_SIZE, 16));
        event.modify(com.alexsmobsup.item.AMItemRegistry.SOPA_DE_MACACO.get(),
                patch -> patch.set(DataComponents.MAX_STACK_SIZE, 16));
    }

    private void clientSetup(FMLClientSetupEvent event) {
    }
}
