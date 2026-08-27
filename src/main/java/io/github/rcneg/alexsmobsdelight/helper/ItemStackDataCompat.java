package io.github.rcneg.alexsmobsdelight.helper;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;

public final class ItemStackDataCompat {
    private ItemStackDataCompat() {
    }

    public static CompoundTag read(ItemStack stack) {
        CompoundTag customData = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        if (customData.contains("tag", Tag.TAG_COMPOUND)) {
            CompoundTag legacyData = customData.getCompound("tag").copy();
            customData.remove("tag");
            legacyData.merge(customData);
            return legacyData;
        }
        return customData;
    }
}
