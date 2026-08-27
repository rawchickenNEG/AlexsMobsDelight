package io.github.rcneg.alexsmobsdelight.init;

import io.github.rcneg.alexsmobsdelight.AlexsMobsDelight;
import io.github.rcneg.alexsmobsdelight.effects.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EffectRegistry {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, AlexsMobsDelight.MODID);
    public static final DeferredRegister<Potion> POTION = DeferredRegister.create(Registries.POTION, AlexsMobsDelight.MODID);
    public static final DeferredHolder<MobEffect, MobEffect> CROCODILE_CRUSH = MOB_EFFECTS.register("crocodile_crush", () -> new AMDMobEffect(MobEffectCategory.BENEFICIAL, -9999028).addAttributeModifier(Attributes.ATTACK_DAMAGE, ResourceLocation.fromNamespaceAndPath(AlexsMobsDelight.MODID, "crocodile_crush_attack_damage"), 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final DeferredHolder<MobEffect, MobEffect> CROCODILE_TOUGHNESS = MOB_EFFECTS.register("crocodile_toughness", () -> new AMDMobEffect(MobEffectCategory.BENEFICIAL, -9999028).addAttributeModifier(Attributes.ARMOR_TOUGHNESS, ResourceLocation.fromNamespaceAndPath(AlexsMobsDelight.MODID, "crocodile_toughness_armor"), 0.2, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final DeferredHolder<MobEffect, MobEffect> CROCODILE_SHARPNESS = MOB_EFFECTS.register("crocodile_sharpness", () -> new CrocodileSharpnessEffect(MobEffectCategory.BENEFICIAL, -9999028));
    public static final DeferredHolder<MobEffect, MobEffect> CROCODILE_HACKSAW = MOB_EFFECTS.register("crocodile_hacksaw", () -> new AMDMobEffect(MobEffectCategory.BENEFICIAL, -9999028).addAttributeModifier(Attributes.ATTACK_SPEED, ResourceLocation.fromNamespaceAndPath(AlexsMobsDelight.MODID, "crocodile_hacksaw_attack_speed"), 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final DeferredHolder<MobEffect, MobEffect> CROCODILE_DEATH_ROLL = MOB_EFFECTS.register("crocodile_death_roll", () -> new CrocodileDeathRollEffect(MobEffectCategory.BENEFICIAL, -9999028));
    public static final DeferredHolder<MobEffect, MobEffect> SEAGULL_ANOREXIA = MOB_EFFECTS.register("seagull_anorexia", () -> new SeagullAnorexiaEffect(MobEffectCategory.BENEFICIAL, -3910904));
    public static final DeferredHolder<MobEffect, MobEffect> CRYSTALLIZE_WALKER = MOB_EFFECTS.register("crystallize_walker", () -> new CrystallizeWalkerEffect(MobEffectCategory.BENEFICIAL, -4184));
    public static final DeferredHolder<MobEffect, MobEffect> POISON_FANGS = MOB_EFFECTS.register("poison_fangs", () -> new AMDMobEffect(MobEffectCategory.BENEFICIAL, -9999028));
    public static final DeferredHolder<MobEffect, MobEffect> FLUTTERING = MOB_EFFECTS.register("fluttering", () -> new AMDMobEffect(MobEffectCategory.BENEFICIAL, -9999028));
    public static final DeferredHolder<MobEffect, MobEffect> DODGE = MOB_EFFECTS.register("dodge", () -> new AMDMobEffect(MobEffectCategory.BENEFICIAL, -9999028));

    public static final DeferredHolder<MobEffect, MobEffect> EXTENDED_TOUCH = MOB_EFFECTS.register("extended_touch", () -> new AMDMobEffect(MobEffectCategory.BENEFICIAL, -4184).addAttributeModifier(Attributes.BLOCK_INTERACTION_RANGE, ResourceLocation.fromNamespaceAndPath(AlexsMobsDelight.MODID, "extended_touch"), 1, AttributeModifier.Operation.ADD_VALUE));
    public static final DeferredHolder<MobEffect, MobEffect> EXTENDED_SCARE = MOB_EFFECTS.register("extended_scare", () -> new AMDMobEffect(MobEffectCategory.BENEFICIAL, -4184).addAttributeModifier(Attributes.ENTITY_INTERACTION_RANGE, ResourceLocation.fromNamespaceAndPath(AlexsMobsDelight.MODID, "extended_scare"), 1, AttributeModifier.Operation.ADD_VALUE));

}
