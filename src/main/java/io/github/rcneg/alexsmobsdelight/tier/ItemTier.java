package io.github.rcneg.alexsmobsdelight.tier;

import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import io.github.rcneg.alexsmobsdelight.init.ItemRegistry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ItemTier {
    public static final Tier CROCODILE = new ForgeTier(2, 250, 8.0F, 3.0F, 10,
            BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(ItemRegistry.CROCODILE_TOOTH.get()));
    public static final Tier CROCODILE_SCUTE = new ForgeTier(2, 250, 8.0F, 3.0F, 10,
            BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(AMItemRegistry.CROCODILE_SCUTE.get()));
    public static final Tier SHRIMP = new ForgeTier(3, 300, 12.0F, 3.0F, 5,
            BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(ItemRegistry.LOBSTER_HEAD.get()));
    public static final Tier WHALE_TOOTH = new ForgeTier(2, 400, 7.5F, 2.0F, 5,
            BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(AMItemRegistry.CACHALOT_WHALE_TOOTH.get()));
}