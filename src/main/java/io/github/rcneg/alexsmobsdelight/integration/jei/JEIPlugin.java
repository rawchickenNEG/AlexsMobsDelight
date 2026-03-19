package io.github.rcneg.alexsmobsdelight.integration.jei;

import com.google.common.collect.ImmutableList;
import io.github.rcneg.alexsmobsdelight.AlexsMobsDelight;
import io.github.rcneg.alexsmobsdelight.integration.jei.category.KiviakDecompositionRecipeCategory;
import io.github.rcneg.alexsmobsdelight.integration.jei.resource.KiviakDecompositionDummy;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.*;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import vectorwing.farmersdelight.client.gui.CookingPotScreen;
import vectorwing.farmersdelight.common.block.entity.container.CookingPotMenu;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModMenuTypes;
import vectorwing.farmersdelight.integration.jei.FDRecipeTypes;
import vectorwing.farmersdelight.integration.jei.FDRecipes;
import vectorwing.farmersdelight.integration.jei.category.DecompositionRecipeCategory;
import vectorwing.farmersdelight.integration.jei.resource.DecompositionDummy;

import javax.annotation.ParametersAreNonnullByDefault;

@JeiPlugin
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@SuppressWarnings("unused")
public class JEIPlugin implements IModPlugin
{
    private static final ResourceLocation ID = new ResourceLocation(AlexsMobsDelight.MODID, "jei_plugin");

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new KiviakDecompositionRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        FDRecipes modRecipes = new FDRecipes();
        registration.addRecipes(
                RecipeType.create(AlexsMobsDelight.MODID, "kiviak_decomposition", KiviakDecompositionDummy.class),
                ImmutableList.of(new KiviakDecompositionDummy())
        );
    }

    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
    }

    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
    }

    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
    }

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }
}
