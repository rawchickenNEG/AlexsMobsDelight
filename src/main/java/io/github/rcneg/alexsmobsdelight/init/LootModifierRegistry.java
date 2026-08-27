package io.github.rcneg.alexsmobsdelight.init;

import com.mojang.serialization.MapCodec;
import io.github.rcneg.alexsmobsdelight.AlexsMobsDelight;
import io.github.rcneg.alexsmobsdelight.loot.ModAddLootModifier;
import io.github.rcneg.alexsmobsdelight.loot.ModLootModifier;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class LootModifierRegistry {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIER = DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, AlexsMobsDelight.MODID);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<ModLootModifier>> ADD_ITEM = LOOT_MODIFIER.register("add_item", () -> ModLootModifier.CODEC);
    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<ModAddLootModifier>> ADD_LOOT_TABLE = LOOT_MODIFIER.register("add_loot_table", () -> ModAddLootModifier.CODEC);
}
