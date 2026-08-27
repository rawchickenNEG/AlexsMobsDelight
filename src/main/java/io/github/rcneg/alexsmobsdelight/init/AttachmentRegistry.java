package io.github.rcneg.alexsmobsdelight.init;

import io.github.rcneg.alexsmobsdelight.AlexsMobsDelight;
import io.github.rcneg.alexsmobsdelight.data.SeagullFoodData;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public final class AttachmentRegistry {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, AlexsMobsDelight.MODID);

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<SeagullFoodData>> SEAGULL_FOOD_DATA =
            ATTACHMENT_TYPES.register(
                    "seagull_food_data",
                    () -> AttachmentType.serializable(SeagullFoodData::new).build()
            );

    private AttachmentRegistry() {
    }
}
