package io.github.rcneg.alexsmobsdelight.init;

import com.github.alexthe666.alexsmobs.effect.AMEffectRegistry;
import com.mojang.blaze3d.shaders.Effect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import vectorwing.farmersdelight.common.registry.ModEffects;

public class AMDFoodValues {
    public static final FoodProperties RAW_BEAR_MEAT = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.3F).meat().build();
    public static final FoodProperties RAW_BEAR_MEAT_SLICE = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).meat().build();
    public static final FoodProperties COOKED_BEAR_MEAT = (new FoodProperties.Builder()).nutrition(8).saturationMod(0.6F).meat().build();
    public static final FoodProperties COOKED_BEAR_MEAT_SLICE = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.6F).meat().build();

    public static final FoodProperties RAW_BISON_MEAT = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.3F).meat().build();
    public static final FoodProperties RAW_BISON_MEAT_CUBES = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).meat().build();
    public static final FoodProperties COOKED_BISON_MEAT = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.6F).meat().build();
    public static final FoodProperties COOKED_BISON_MEAT_CUBES = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.6F).meat().build();

    public static final FoodProperties RAW_TUSKLIN_MEAT = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.3F).meat().build();
    public static final FoodProperties RAW_TUSKLIN_MEAT_PIECE = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).meat().build();
    public static final FoodProperties COOKED_TUSKLIN_MEAT = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.6F).meat().build();
    public static final FoodProperties COOKED_TUSKLIN_MEAT_PIECE = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.6F).meat().build();
    public static final FoodProperties RAW_TUSKLIN_SAUSAGE = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.4F).build();
    public static final FoodProperties SMOKED_TUSKLIN_SAUSAGE = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.85F).build();
    public static final FoodProperties SLICE_OF_SMOKED_TUSKLIN_SAUSAGE = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.85F).build();
    public static final FoodProperties FRIED_TUSKLIN_MEAT = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.85F).meat().build();

    public static final FoodProperties RAW_KANGAROO_MEAT_SLICE = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.6F).meat().build();
    public static final FoodProperties COOKED_KANGAROO_MEAT_SLICE = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.85F).meat().build();

    public static final FoodProperties RAW_MOOSE_RIB_PIECE = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.6F).meat().build();
    public static final FoodProperties COOKED_MOOSE_RIB_PIECE = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.85F).meat().build();

    public static final FoodProperties RAW_SEAGULL = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.3F).meat().build();
    public static final FoodProperties COOKED_SEAGULL = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.6F).meat().build();
    public static final FoodProperties ALWAYS_EAT_COOKED_SEAGULL = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.6F).alwaysEat().build();

    public static final FoodProperties MANTIS_SHRIMP_TAIL = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).build();
    public static final FoodProperties COOKED_MANTIS_SHRIMP_TAIL = (new FoodProperties.Builder()).nutrition(7).saturationMod(0.6F).build();

    public static final FoodProperties BANANA_SLICE = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).build();
    public static final FoodProperties BANANA_ROLL = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.6F).build();
    public static final FoodProperties BANANA_ROLL_SLICE = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.5F).build();
    public static final FoodProperties LOBSTER_ROLL = (new FoodProperties.Builder()).nutrition(8).saturationMod(0.6F).build();

    public static final FoodProperties RAW_CROCODILE_CLAW = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.3F).meat().build();
    public static final FoodProperties RAW_CROCODILE_TAIL = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.3F).meat().build();
    public static final FoodProperties RAW_CROCODILE_MEAT = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).meat().build();
    public static final FoodProperties COOKED_CROCODILE_CLAW = (new FoodProperties.Builder()).nutrition(8).saturationMod(0.6F).meat().build();
    public static final FoodProperties COOKED_CROCODILE_TAIL = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.6F).meat().build();
    public static final FoodProperties COOKED_CROCODILE_MEAT = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.6F).meat().build();
    public static final FoodProperties CROCODILE_BARBECUE_STICK = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.9F).build();

    public static final FoodProperties RAW_TENTACLE = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).build();
    public static final FoodProperties COOKED_TENTACLE = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.6F).build();
    public static final FoodProperties TENTACLE_MEDLEY = (new FoodProperties.Builder()).nutrition(10).saturationMod(0.6F).build();
    public static final FoodProperties TENTACLE_SANDWICH = (new FoodProperties.Builder()).nutrition(10).saturationMod(0.6F).build();

    public static final FoodProperties MOOSE_PIE = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.3F).build();
    public static final FoodProperties MIMICANDY = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.5F).build();


    public static final FoodProperties BOWL_OF_WILD_STEW = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.8F).effect(() -> {
        return new MobEffectInstance((MobEffect) ModEffects.COMFORT.get(), 6000, 0);
    }, 1.0F).build();
    public static final FoodProperties CROCODILE_STEW_WITH_CATFISH = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.8F).effect(() -> {
        return new MobEffectInstance((MobEffect)ModEffects.COMFORT.get(), 3600, 0);
    }, 1.0F).effect(() -> {
        return new MobEffectInstance((MobEffect)EffectRegistry.CROCODILE_TOUGHNESS.get(), 1800, 0);
    }, 1.0F).build();
    public static final FoodProperties FRONTIER_SOUP = (new FoodProperties.Builder()).nutrition(10).saturationMod(0.6F).effect(() -> {
        return new MobEffectInstance((MobEffect)ModEffects.COMFORT.get(), 3000, 0);
    }, 1.0F).effect(() -> {
        return new MobEffectInstance((MobEffect)MobEffects.MOVEMENT_SPEED, 3000, 0);
    }, 1.0F).build();
    public static final FoodProperties KANGAROO_MEAT_STEW = (new FoodProperties.Builder()).nutrition(8).saturationMod(0.8F).effect(() -> {
        return new MobEffectInstance((MobEffect)ModEffects.COMFORT.get(), 3000, 0);
    }, 1.0F).build();
    public static final FoodProperties MOOSE_STEW = (new FoodProperties.Builder()).nutrition(8).saturationMod(0.8F).effect(() -> {
        return new MobEffectInstance((MobEffect)ModEffects.COMFORT.get(), 3000, 0);
    }, 1.0F).build();
    public static final FoodProperties SEA_BEAR_STEW = (new FoodProperties.Builder()).nutrition(9).saturationMod(0.8F).effect(() -> {
        return new MobEffectInstance((MobEffect)ModEffects.COMFORT.get(), 6000, 0);
    }, 1.0F).build();
    public static final FoodProperties SEAGULL_SOUP = (new FoodProperties.Builder()).nutrition(7).saturationMod(0.6F).effect(() -> {
        return new MobEffectInstance((MobEffect)ModEffects.COMFORT.get(), 3000, 0);
    }, 1.0F).build();
    public static final FoodProperties LOBSTER_HEAD_STEW = (new FoodProperties.Builder()).nutrition(8).saturationMod(0.8F).effect(() -> {
        return new MobEffectInstance((MobEffect)ModEffects.COMFORT.get(), 3000, 0);
    }, 1.0F).build();
    public static final FoodProperties BISON_TARTARE = (new FoodProperties.Builder()).nutrition(7).saturationMod(0.5F).effect(() -> {
        return new MobEffectInstance((MobEffect)MobEffects.CONFUSION, 100, 0);
    }, 0.2F).effect(() -> {
        return new MobEffectInstance((MobEffect)ModEffects.NOURISHMENT.get(), 3000, 0);
    }, 1.0F).build();
    public static final FoodProperties BREASTED_KANGAROO_MEATBALLS = (new FoodProperties.Builder()).nutrition(10).saturationMod(0.6F).effect(() -> {
        return new MobEffectInstance((MobEffect)ModEffects.NOURISHMENT.get(), 3000, 0);
    }, 1.0F).build();
    public static final FoodProperties GRAVY_KANGAROO_MEAT = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.7F).effect(() -> {
        return new MobEffectInstance((MobEffect)ModEffects.NOURISHMENT.get(), 3000, 0);
    }, 1.0F).build();
    public static final FoodProperties BOWL_OF_HONEY_GLAZED_BEAR_MEAT_WITH_SALMON = (new FoodProperties.Builder()).nutrition(16).saturationMod(0.85F).effect(() -> {
        return new MobEffectInstance((MobEffect)ModEffects.NOURISHMENT.get(), 3000, 0);
    }, 1.0F).effect(() -> {
        return new MobEffectInstance((MobEffect) AMEffectRegistry.KNOCKBACK_RESISTANCE.get(), 1800, 0);
    }, 1.0F).build();
    public static final FoodProperties HONEY_GLAZED_MOOSE_RIBS = (new FoodProperties.Builder()).nutrition(14).saturationMod(0.85F).effect(() -> {
        return new MobEffectInstance((MobEffect)ModEffects.NOURISHMENT.get(), 6000, 0);
    }, 1.0F).build();
    public static final FoodProperties ROAST_SEAGULL = (new FoodProperties.Builder()).nutrition(14).saturationMod(0.85F).effect(() -> {
        return new MobEffectInstance((MobEffect)ModEffects.NOURISHMENT.get(), 6000, 0);
    }, 1.0F).build();
    public static final FoodProperties CANNED_BEAR_MEAT = (new FoodProperties.Builder()).nutrition(20).saturationMod(0.6F).effect(() -> {
        return new MobEffectInstance((MobEffect)MobEffects.DAMAGE_BOOST, 600, 1);
    }, 1.0F).effect(() -> {
        return new MobEffectInstance((MobEffect) AMEffectRegistry.KNOCKBACK_RESISTANCE.get(), 1800, 0);
    }, 1.0F).build();
    public static final FoodProperties FRIED_SEAGULL_WITH_FRIES = (new FoodProperties.Builder()).nutrition(14).saturationMod(0.85F).effect(() -> {
        return new MobEffectInstance((MobEffect)MobEffects.SATURATION, 200, 0);
    }, 1.0F).build();

    public static final FoodProperties BIG_MAC = (new FoodProperties.Builder()).nutrition(16).saturationMod(0.85F).effect(() -> {
        return new MobEffectInstance((MobEffect)MobEffects.DAMAGE_BOOST, 600, 1);
    }, 1.0F).effect(() -> {
        return new MobEffectInstance((MobEffect)MobEffects.ABSORPTION, 1800, 0);
    }, 1.0F).build();
    public static final FoodProperties MANTIS_SHRIMP_TEMPURA = (new FoodProperties.Builder()).nutrition(8).saturationMod(0.6F).effect(() -> {
        return new MobEffectInstance((MobEffect)MobEffects.DAMAGE_BOOST, 600, 0);
    }, 1.0F).build();
    public static final FoodProperties SHRIMP_FRIED_EGG = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.3F).build();
    public static final FoodProperties SHRIMP_POKE_BOWL = (new FoodProperties.Builder()).nutrition(20).saturationMod(0.85F).effect(() -> {
        return new MobEffectInstance((MobEffect)MobEffects.DAMAGE_BOOST, 600, 1);
    }, 1.0F).effect(() -> {
        return new MobEffectInstance((MobEffect)ModEffects.NOURISHMENT.get(), 6000, 0);
    }, 1.0F).build();
    public static final FoodProperties BOWL_OF_MOOSE_SAUSAGE_WITH_SALMON = (new FoodProperties.Builder()).nutrition(14).saturationMod(0.85F).effect(() -> {
        return new MobEffectInstance((MobEffect)ModEffects.NOURISHMENT.get(), 6000, 0);
    }, 1.0F).build();
    public static final FoodProperties LOBSTER_PASTA = (new FoodProperties.Builder()).nutrition(14).saturationMod(0.85F).effect(() -> {
        return new MobEffectInstance((MobEffect)ModEffects.NOURISHMENT.get(), 6000, 0);
    }, 1.0F).build();
    public static final FoodProperties TUSKLIN_HOTDOG = (new FoodProperties.Builder()).nutrition(20).saturationMod(0.85F).effect(() -> {
        return new MobEffectInstance((MobEffect)MobEffects.DAMAGE_RESISTANCE, 600, 1);
    }, 1.0F).effect(() -> {
        return new MobEffectInstance((MobEffect)MobEffects.ABSORPTION, 1800, 0);
    }, 1.0F).build();
    public static final FoodProperties SMOKED_TUSKLIN_SANDWICH = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.85F).build();
    public static final FoodProperties KATSUDON = (new FoodProperties.Builder()).nutrition(18).saturationMod(0.85F).effect(() -> {
        return new MobEffectInstance((MobEffect)MobEffects.DAMAGE_RESISTANCE, 600, 1);
    }, 1.0F).effect(() -> {
        return new MobEffectInstance((MobEffect)ModEffects.NOURISHMENT.get(), 6000, 0);
    }, 1.0F).build();
    public static final FoodProperties STUFFED_CROCODILE_CLAW = (new FoodProperties.Builder()).nutrition(16).saturationMod(0.85F).effect(() -> {
        return new MobEffectInstance((MobEffect)EffectRegistry.CROCODILE_CRUSH.get(), 1800, 0);
    }, 1.0F).effect(() -> {
        return new MobEffectInstance((MobEffect)ModEffects.NOURISHMENT.get(), 6000, 0);
    }, 1.0F).build();
    public static final FoodProperties STUFFED_CROCODILE_TAIL = (new FoodProperties.Builder()).nutrition(18).saturationMod(0.85F).effect(() -> {
        return new MobEffectInstance((MobEffect)EffectRegistry.CROCODILE_HACKSAW.get(), 1800, 0);
    }, 1.0F).effect(() -> {
        return new MobEffectInstance((MobEffect)ModEffects.NOURISHMENT.get(), 6000, 0);
    }, 1.0F).build();
    public static final FoodProperties STUFFED_CROCODILE_HEAD = (new FoodProperties.Builder()).nutrition(20).saturationMod(0.85F).effect(() -> {
        return new MobEffectInstance((MobEffect)EffectRegistry.CROCODILE_SHARPNESS.get(), 1800, 0);
    }, 1.0F).effect(() -> {
        return new MobEffectInstance((MobEffect)ModEffects.NOURISHMENT.get(), 6000, 0);
    }, 1.0F).build();
    public static final FoodProperties STUFFED_CROCODILE = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.85F).effect(() -> {
        return new MobEffectInstance((MobEffect)EffectRegistry.CROCODILE_TOUGHNESS.get(), 1800, 0);
    }, 1.0F).effect(() -> {
        return new MobEffectInstance((MobEffect)ModEffects.NOURISHMENT.get(), 6000, 0);
    }, 1.0F).build();
    public static final FoodProperties TAKOYAKI = (new FoodProperties.Builder()).nutrition(10).saturationMod(0.85F).effect(() -> {
        return new MobEffectInstance((MobEffect)ModEffects.NOURISHMENT.get(), 3000, 0);
    }, 1.0F).build();
    public static final FoodProperties CTHULHUS_BREAKFAST = (new FoodProperties.Builder()).nutrition(13).saturationMod(0.85F).effect(() -> {
        return new MobEffectInstance((MobEffect)MobEffects.DARKNESS, 1200, 0);
    }, 1.0F).effect(() -> {
        return new MobEffectInstance((MobEffect)AMEffectRegistry.FEAR.get(), 100, 0);
    }, 1.0F).effect(() -> {
        return new MobEffectInstance((MobEffect)MobEffects.DIG_SPEED, 1200, 4);
    }, 1.0F).build();

    //public static final Map<Item, FoodProperties> VANILLA_SOUP_EFFECTS;


    public AMDFoodValues() {
    }

    static {
        /*
        VANILLA_SOUP_EFFECTS = (new ImmutableMap.Builder()).put(Items.MUSHROOM_STEW, (new FoodProperties.Builder()).effect(() -> {
            return new MobEffectInstance((MobEffect)ModEffects.COMFORT.get(), 3600, 0);
        }, 1.0F).build()).put(Items.BEETROOT_SOUP, (new FoodProperties.Builder()).effect(() -> {
            return new MobEffectInstance((MobEffect)ModEffects.COMFORT.get(), 3600, 0);
        }, 1.0F).build()).put(Items.RABBIT_STEW, (new FoodProperties.Builder()).effect(() -> {
            return new MobEffectInstance((MobEffect)ModEffects.COMFORT.get(), 6000, 0);
        }, 1.0F).build()).build();

         */
    }
}
