package io.github.rcneg.alexsmobsdelight.integration.jei.category;

import com.google.common.collect.ImmutableList;
import io.github.rcneg.alexsmobsdelight.AlexsMobsDelight;
import io.github.rcneg.alexsmobsdelight.init.ItemRegistry;
import io.github.rcneg.alexsmobsdelight.integration.jei.TooltipCallback;
import io.github.rcneg.alexsmobsdelight.integration.jei.resource.KiviakDecompositionDummy;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.common.utility.ClientRenderUtils;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class KiviakDecompositionRecipeCategory implements IRecipeCategory<KiviakDecompositionDummy>
{
    private static final int slotSize = 22;

    private final Component title;
    private final IDrawable background;
    private final IDrawable slotIcon;
    private final IDrawable icon;

    public KiviakDecompositionRecipeCategory(IGuiHelper helper) {
        title = Component.translatable(AlexsMobsDelight.MODID + ".jei.kiviak_decomposition");
        ResourceLocation backgroundImage = new ResourceLocation(AlexsMobsDelight.MODID, "textures/gui/jei/kiviak_decomposition.png");
        background = helper.createDrawable(backgroundImage, 0, 0, 118, 80);
        icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ItemRegistry.POLAR_KIVIAK.get()));
        slotIcon = helper.createDrawable(backgroundImage, 119, 0, slotSize, slotSize);
    }

    @Override
    public RecipeType<KiviakDecompositionDummy> getRecipeType() {
        return RecipeType.create(AlexsMobsDelight.MODID, "kiviak_decomposition", KiviakDecompositionDummy.class);
    }

    @Override
    public Component getTitle() {
        return this.title;
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, KiviakDecompositionDummy recipe, IFocusGroup focusGroup) {
        List<ItemStack> accelerators = ForgeRegistries.BLOCKS.tags().getTag(BlockTags.ICE).stream().map(ItemStack::new).collect(Collectors.toList());
        List<ItemStack> knives = ForgeRegistries.ITEMS.tags().getTag(ModTags.KNIVES).stream().map(ItemStack::new).toList();

        List<ItemStack> inputs = List.of(new ItemStack(ItemRegistry.COASTAL_KIVIAK.get()), new ItemStack(ItemRegistry.POLAR_KIVIAK.get()));
        ItemStack result = new ItemStack(ItemRegistry.KIVIAK.get(), 6);
        builder.addSlot(RecipeIngredientRole.INPUT, 9, 26).addItemStacks(inputs);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 93, 26).addItemStack(result).addTooltipCallback(new TooltipCallback());
        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 93, 2).addItemStacks(knives);
        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 64, 54).addItemStacks(accelerators);
    }

    @Override
    public void draw(KiviakDecompositionDummy recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        this.slotIcon.draw(guiGraphics, 63, 53);
    }

    @Override
    public List<Component> getTooltipStrings(KiviakDecompositionDummy recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        if (ClientRenderUtils.isCursorInsideBounds(40, 38, 11, 11, mouseX, mouseY)) {
            return ImmutableList.of(translateKey(".light"));
        }
        if (ClientRenderUtils.isCursorInsideBounds(53, 38, 11, 11, mouseX, mouseY)) {
            return ImmutableList.of(translateKey(".biome"));
        }
        if (ClientRenderUtils.isCursorInsideBounds(67, 38, 11, 11, mouseX, mouseY)) {
            return ImmutableList.of(translateKey(".accelerators"));
        }
        return Collections.emptyList();
    }

    private static MutableComponent translateKey(@Nonnull String suffix) {
        return Component.translatable(AlexsMobsDelight.MODID + ".jei.kiviak_decomposition" + suffix);
    }
}