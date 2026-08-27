package io.github.rcneg.alexsmobsdelight.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.neoforged.neoforge.common.NeoForgeMod;

public class CrocodileSword extends SwordItem {
    public CrocodileSword(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
        super(p_43269_, p_43272_.attributes(SwordItem.createAttributes(p_43269_, p_43270_, p_43271_)
                .withModifierAdded(NeoForgeMod.SWIM_SPEED,
                        new AttributeModifier(ResourceLocation.fromNamespaceAndPath("alexsmobsdelight", "crocodile_swim_speed"), 0.5f, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND)));
    }
}
