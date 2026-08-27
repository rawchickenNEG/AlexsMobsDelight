package io.github.rcneg.alexsmobsdelight.init;

import io.github.rcneg.alexsmobsdelight.AlexsMobsDelight;
import io.github.rcneg.alexsmobsdelight.recipes.RecipeMimicandyDuplicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RecipeRegistry {
    public static final DeferredRegister<RecipeSerializer<?>> DEF_REG;
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<?>> MIMICANDY_RECIPE;

    public RecipeRegistry() {
    }

    public static void init() {
    }

    static {
        DEF_REG = DeferredRegister.create(Registries.RECIPE_SERIALIZER, AlexsMobsDelight.MODID);
        MIMICANDY_RECIPE = DEF_REG.register("mimicandy_duplicate", () ->
                new SimpleCraftingRecipeSerializer<>(RecipeMimicandyDuplicate::new));
    }
}