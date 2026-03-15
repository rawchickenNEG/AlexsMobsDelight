package io.github.rcneg.alexsmobsdelight.init;

import io.github.rcneg.alexsmobsdelight.AlexsMobsDelight;
import io.github.rcneg.alexsmobsdelight.effects.AMDMobEffect;
import io.github.rcneg.alexsmobsdelight.effects.CrocodileDeathRollEffect;
import io.github.rcneg.alexsmobsdelight.effects.CrocodileSharpnessEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EffectRegistry {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, AlexsMobsDelight.MODID);
    public static final DeferredRegister<Potion> POTION = DeferredRegister.create(ForgeRegistries.POTIONS, AlexsMobsDelight.MODID);
    public static final RegistryObject<MobEffect> CROCODILE_CRUSH = MOB_EFFECTS.register("crocodile_crush", () -> new AMDMobEffect(MobEffectCategory.BENEFICIAL, -9999028).addAttributeModifier(Attributes.ATTACK_DAMAGE, "90185901-376B-4498-935B-2F7F79060635", 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final RegistryObject<MobEffect> CROCODILE_TOUGHNESS = MOB_EFFECTS.register("crocodile_toughness", () -> new AMDMobEffect(MobEffectCategory.BENEFICIAL, -9999028).addAttributeModifier(Attributes.ARMOR_TOUGHNESS, "90185901-1310-4498-935B-2F7F79060635", 0.2, AttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final RegistryObject<MobEffect> CROCODILE_SHARPNESS = MOB_EFFECTS.register("crocodile_sharpness", () -> new CrocodileSharpnessEffect(MobEffectCategory.BENEFICIAL, -9999028));
    public static final RegistryObject<MobEffect> CROCODILE_HACKSAW = MOB_EFFECTS.register("crocodile_hacksaw", () -> new AMDMobEffect(MobEffectCategory.BENEFICIAL, -9999028).addAttributeModifier(Attributes.ATTACK_SPEED, "90185901-3461-4498-935B-2F7F79060635", 0.15, AttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final RegistryObject<MobEffect> CROCODILE_DEATH_ROLL = MOB_EFFECTS.register("crocodile_death_roll", () -> new CrocodileDeathRollEffect(MobEffectCategory.BENEFICIAL, -9999028));
}
