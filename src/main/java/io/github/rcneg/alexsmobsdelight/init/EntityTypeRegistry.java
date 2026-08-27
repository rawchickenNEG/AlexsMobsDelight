package io.github.rcneg.alexsmobsdelight.init;

import io.github.rcneg.alexsmobsdelight.AlexsMobsDelight;
import io.github.rcneg.alexsmobsdelight.entities.SuperMosquito;
import io.github.rcneg.alexsmobsdelight.entities.SuperSeagull;
import io.github.rcneg.alexsmobsdelight.entities.ThrownBananaEntity;
import io.github.rcneg.alexsmobsdelight.entities.ThrownDartEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = AlexsMobsDelight.MODID)
public class EntityTypeRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, AlexsMobsDelight.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<ThrownDartEntity>> THROWN_DART = abstractArrow("thrown_dart", ThrownDartEntity::new);
    public static final DeferredHolder<EntityType<?>, EntityType<ThrownBananaEntity>> THROWN_BANANA = abstractArrow("thrown_banana", ThrownBananaEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<SuperSeagull>> SUPER_SEAGULL = register("super_seagull",
            EntityType.Builder.of(SuperSeagull::new, MobCategory.CREATURE).sized(0.9F, 0.9F).setTrackingRange(45));

    public static final DeferredHolder<EntityType<?>, EntityType<SuperMosquito>> SUPER_MOSQUITO = register("super_mosquito",
            EntityType.Builder.of(SuperMosquito::new, MobCategory.MONSTER).sized(1.5F, 1.3F).fireImmune().setTrackingRange(45));
    private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String name, EntityType.Builder<T> entityTypeBuilder) {
        return ENTITY_TYPES.register(name, () -> entityTypeBuilder.build(name));
    }

    private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> throwableItem(String name, EntityType.EntityFactory<T> factory) {
        return ENTITY_TYPES.register(name, () -> (EntityType.Builder.of(factory, MobCategory.MISC).sized(0.25F, 0.25F)
                .clientTrackingRange(4).updateInterval(10).build(name)));
    }

    private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> abstractArrow(String name, EntityType.EntityFactory<T> factory) {
        return ENTITY_TYPES.register(name, () -> (EntityType.Builder.of(factory, MobCategory.MISC).sized(0.5F, 0.5F)
                .clientTrackingRange(4).updateInterval(20).build(name)));
    }

    @SubscribeEvent
    public static void initializeAttributes(EntityAttributeCreationEvent event) {
        event.put(SUPER_SEAGULL.get(), SuperSeagull.bakeAttributes().build());
        event.put(SUPER_MOSQUITO.get(), SuperMosquito.bakeAttributes().build());

    }
}
