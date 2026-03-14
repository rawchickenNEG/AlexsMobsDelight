package io.github.rcneg.alexsmobsdelight.init;

import io.github.rcneg.alexsmobsdelight.AlexsMobsDelight;
import io.github.rcneg.alexsmobsdelight.entities.ThrownBananaEntity;
import io.github.rcneg.alexsmobsdelight.entities.ThrownDartEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = AlexsMobsDelight.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityTypeRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, AlexsMobsDelight.MODID);

    public static final RegistryObject<EntityType<ThrownDartEntity>> THROWN_DART = abstractArrow("thrown_dart", ThrownDartEntity::new);
    public static final RegistryObject<EntityType<ThrownBananaEntity>> THROWN_BANANA = abstractArrow("thrown_banana", ThrownBananaEntity::new);


    private static <T extends Entity> RegistryObject<EntityType<T>> register(String name, EntityType.Builder<T> entityTypeBuilder) {
        return ENTITY_TYPES.register(name, () -> entityTypeBuilder.build(name));
    }

    private static <T extends Entity> RegistryObject<EntityType<T>> throwableItem(String name, EntityType.EntityFactory<T> factory) {
        return ENTITY_TYPES.register(name, () -> (EntityType.Builder.of(factory, MobCategory.MISC).sized(0.25F, 0.25F)
                .clientTrackingRange(4).updateInterval(10).build(name)));
    }

    private static <T extends Entity> RegistryObject<EntityType<T>> abstractArrow(String name, EntityType.EntityFactory<T> factory) {
        return ENTITY_TYPES.register(name, () -> (EntityType.Builder.of(factory, MobCategory.MISC).sized(0.5F, 0.5F)
                .clientTrackingRange(4).updateInterval(20).build(name)));
    }
}
