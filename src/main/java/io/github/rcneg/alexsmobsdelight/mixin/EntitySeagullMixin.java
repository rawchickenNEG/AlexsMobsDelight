package io.github.rcneg.alexsmobsdelight.mixin;

import com.github.alexthe666.alexsmobs.entity.EntitySeagull;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import io.github.rcneg.alexsmobsdelight.accessor.IEntitySeagullData;
import io.github.rcneg.alexsmobsdelight.config.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@Mixin(EntitySeagull.class)
public class EntitySeagullMixin implements IEntitySeagullData {

    @Unique
    private boolean amd$consumedEternalFood;
    @Unique
    private Map<MobEffect, MobEffectInstance> amd$consumedFoodEffects = Maps.newHashMap();

    @Override
    public boolean amd$getConsumedEternalFood() {
        return amd$consumedEternalFood;
    }

    @Override
    public void amd$setConsumedEternalFood(boolean flag) {
        amd$consumedEternalFood = flag;
    }

    @Override
    public Map<MobEffect, MobEffectInstance> amd$getEffects() {
        return amd$consumedFoodEffects;
    }

    @Override
    public void amd$setEffectMap(Map<MobEffect, MobEffectInstance> map) {
        amd$consumedFoodEffects = map;
    }

    @Inject(
            method = "eatItemEffect",
            at = @At("HEAD"),
            remap = false
    )
    private void amd$ApplyEternalTag(ItemStack heldItem, CallbackInfo ci) {
        if (heldItem != null){
            if(Config.ETERNAL_FOODS_ITEMS.contains(heldItem.getItem())){
                EntitySeagull seagull = (EntitySeagull) (Object) this;
                if(seagull instanceof IEntitySeagullData data){
                    data.amd$setConsumedEternalFood(true);
                }
            }
            FoodProperties food = heldItem.getItem().getFoodProperties(heldItem, null);
            if (food != null && !food.getEffects().isEmpty()) {
                List<Pair<MobEffectInstance, Float>> foodEffectsWithChance = food.getEffects();
                Map<MobEffect, MobEffectInstance> foodEffects = Maps.newHashMap();
                EntitySeagull seagull = (EntitySeagull) (Object) this;
                for(Pair<MobEffectInstance, Float> effectPair : foodEffectsWithChance){
                    if(seagull.getRandom().nextFloat() <= effectPair.getSecond()){
                        MobEffectInstance effectIns = effectPair.getFirst();
                        foodEffects.put(effectIns.getEffect(), effectIns);
                    }
                }
                if(seagull instanceof IEntitySeagullData data){
                    data.amd$setEffectMap(foodEffects);
                }
            }
        }
    }

    @Inject(method = "setTreasurePos", at = @At("TAIL"), remap = false)
    private void amd$setTreasureSand(BlockPos pos, CallbackInfo ci) {
        EntitySeagull seagull = (EntitySeagull) (Object) this;
        Level level = seagull.level();
        for(int i = 0; i < 128; ++i) {
            BlockPos pos1 = new BlockPos(pos.getX(), i, pos.getZ());
            if (level.getBlockState(pos1).getBlock() instanceof ChestBlock && level.getBlockState(pos1.below()).is(Blocks.SAND)) {
                boolean flag = true;
                for(Direction d : Direction.values()){
                    if(!level.getBlockState(pos1.relative(d)).isSolid()){
                        flag = false;
                    }
                }
                if(flag){
                    BlockState treasureSand = Blocks.SUSPICIOUS_SAND.defaultBlockState();
                    if(level.setBlock(pos1.below(), treasureSand, 3)){
                        BlockEntity entity = level.getBlockEntity(pos1.below());
                        if(entity instanceof BrushableBlockEntity brushable){
                            BlockEntity entity1 = level.getBlockEntity(pos1);
                            if(entity1 instanceof ChestBlockEntity chestEntity){
                                CompoundTag tag = chestEntity.getPersistentData();
                                if(tag.contains("LootTable")){
                                    brushable.setLootTable(new ResourceLocation("alexsmobsdelight:gameplay/seagull_treasure_sand"), 0);
                                }
                            }
                        }
                    }
                    break;
                }
            }
        }

    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void amd$save(CompoundTag tag, CallbackInfo ci) {
        tag.putBoolean("AmdConsumedEternalFood", this.amd$consumedEternalFood);
        if (!this.amd$consumedFoodEffects.isEmpty()) {
            ListTag listtag = new ListTag();
            for (MobEffectInstance mobeffectinstance : this.amd$consumedFoodEffects.values()) {
                listtag.add(mobeffectinstance.save(new CompoundTag()));
            }
            tag.put("AmdConsumedFoodEffects", listtag);
        }
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void amd$load(CompoundTag tag, CallbackInfo ci) {
        if (tag.contains("AmdConsumedEternalFood")){
            this.amd$consumedEternalFood = tag.getBoolean("AmdConsumedEternalFood");
        }
        if (tag.contains("AmdConsumedFoodEffects", 9)) {
            ListTag listtag = tag.getList("AmdConsumedFoodEffects", 10);

            for(int i = 0; i < listtag.size(); ++i) {
                CompoundTag compoundtag = listtag.getCompound(i);
                MobEffectInstance mobeffectinstance = MobEffectInstance.load(compoundtag);
                if (mobeffectinstance != null) {
                    this.amd$consumedFoodEffects.put(mobeffectinstance.getEffect(), mobeffectinstance);
                }
            }
        }
    }
}
