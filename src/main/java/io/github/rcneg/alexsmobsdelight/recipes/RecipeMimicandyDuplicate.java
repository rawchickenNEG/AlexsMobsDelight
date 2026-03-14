package io.github.rcneg.alexsmobsdelight.recipes;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.misc.AMRecipeRegistry;
import io.github.rcneg.alexsmobsdelight.init.ItemRegistry;
import io.github.rcneg.alexsmobsdelight.init.RecipeRegistry;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeMimicandyDuplicate extends CustomRecipe {
    public RecipeMimicandyDuplicate(ResourceLocation idIn, CraftingBookCategory category) {
        super(idIn, category);
    }

    public boolean matches(CraftingContainer inv, Level worldIn) {
        if (!AMConfig.mimicreamRepair) {
            return false;
        } else {
            ItemStack edibleStack = ItemStack.EMPTY;
            int mimicreamCount = 0;

            for(int j = 0; j < inv.getContainerSize(); ++j) {
                ItemStack itemstack1 = inv.getItem(j);
                if (!itemstack1.isEmpty()) {
                    if (itemstack1.isEdible() && !this.isBlacklisted(itemstack1) && itemstack1.getItem() != ItemRegistry.MIMICANDY.get()) {
                        edibleStack = itemstack1;
                    } else if (itemstack1.getItem() == ItemRegistry.MIMICANDY.get()) {
                        ++mimicreamCount;
                    }
                }
            }

            return !edibleStack.isEmpty() && mimicreamCount >= 8;
        }
    }

    public boolean isBlacklisted(ItemStack stack) {
        ResourceLocation name = ForgeRegistries.ITEMS.getKey(stack.getItem());
        return name != null && AMConfig.mimicreamBlacklist.contains(name.toString());
    }

    public ItemStack assemble(CraftingContainer inv, RegistryAccess registryAccess) {
        ItemStack edibleStack = ItemStack.EMPTY;
        int mimicreamCount = 0;

        for(int j = 0; j < inv.getContainerSize(); ++j) {
            ItemStack itemstack1 = inv.getItem(j);
            if (!itemstack1.isEmpty()) {
                if (itemstack1.isEdible() && !this.isBlacklisted(itemstack1) && itemstack1.getItem() != ItemRegistry.MIMICANDY.get()) {
                    edibleStack = itemstack1;
                } else if (itemstack1.getItem() == ItemRegistry.MIMICANDY.get()) {
                    ++mimicreamCount;
                }
            }
        }

        if (!edibleStack.isEmpty() && mimicreamCount >= 8) {
            return new ItemStack(edibleStack.getItem(), 1);
        } else {
            return ItemStack.EMPTY;
        }
    }

    public NonNullList<ItemStack> getRemainingItems(CraftingContainer inv) {
        NonNullList<ItemStack> nonnulllist = NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);

        for(int i = 0; i < nonnulllist.size(); ++i) {
            ItemStack itemstack = inv.getItem(i);
            if (itemstack.getItem().isEdible() && itemstack.getItem() != ItemRegistry.MIMICANDY.get()) {
                ItemStack itemstack1 = itemstack.copy();
                itemstack1.setCount(1);
                nonnulllist.set(i, itemstack1);
                break;
            }
        }

        return nonnulllist;
    }

    public RecipeSerializer<?> getSerializer() {
        return RecipeRegistry.MIMICANDY_RECIPE.get();
    }

    public boolean canCraftInDimensions(int width, int height) {
        return width >= 3 && height >= 3;
    }
}
