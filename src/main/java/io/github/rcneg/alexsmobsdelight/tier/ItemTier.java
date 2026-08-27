package io.github.rcneg.alexsmobsdelight.tier;

import com.alexsmobsup.item.AMItemRegistry;
import io.github.rcneg.alexsmobsdelight.init.ItemRegistry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ItemTier {
    public static final Tier CROCODILE = new SimpleTier(BlockTags.NEEDS_IRON_TOOL, 250, 8.0F, 3.0F, 10,
            () -> Ingredient.of(ItemRegistry.CROCODILE_TOOTH.get()));
    public static final Tier CROCODILE_SCUTE = new SimpleTier(BlockTags.NEEDS_IRON_TOOL, 250, 8.0F, 3.0F, 10,
            () -> Ingredient.of(AMItemRegistry.CROCODILE_SCUTE.get()));
    public static final Tier SHRIMP = new SimpleTier(BlockTags.NEEDS_IRON_TOOL, 300, 12.0F, 3.0F, 5,
            () -> Ingredient.of(ItemRegistry.LOBSTER_HEAD.get()));
    public static final Tier WHALE_TOOTH = new SimpleTier(BlockTags.NEEDS_IRON_TOOL, 400, 7.5F, 2.0F, 5,
            () -> Ingredient.of(AMItemRegistry.CACHALOT_WHALE_TOOTH.get()));
}