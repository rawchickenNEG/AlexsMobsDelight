package io.github.rcneg.alexsmobsdelight.loot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

import javax.annotation.Nonnull;

import static net.minecraft.world.level.storage.loot.LootTable.createStackSplitter;

public class ModAddLootModifier extends LootModifier {
    public static final MapCodec<ModAddLootModifier> CODEC = RecordCodecBuilder.mapCodec(inst -> codecStart(inst)
                    .and(ResourceLocation.CODEC.fieldOf("lootTable").forGetter((m) -> m.lootTable))
                    .apply(inst, ModAddLootModifier::new));
    private final ResourceLocation lootTable;

    protected ModAddLootModifier(LootItemCondition[] conditionsIn, ResourceLocation lootTable) {
        super(conditionsIn);
        this.lootTable = lootTable;
    }

    @Nonnull
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        LootTable extraTable = context.getLevel().getServer().reloadableRegistries()
                .getLootTable(ResourceKey.create(Registries.LOOT_TABLE, this.lootTable));
        extraTable.getRandomItemsRaw(context, createStackSplitter(context.getLevel(), generatedLoot::add));
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
