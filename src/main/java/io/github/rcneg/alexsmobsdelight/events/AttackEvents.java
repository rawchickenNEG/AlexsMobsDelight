package io.github.rcneg.alexsmobsdelight.events;

import com.alexsmobsup.effect.AMEffectRegistry;
import com.alexsmobsup.entity.EntityTarantulaHawk;
import com.alexsmobsup.misc.AMSoundRegistry;
import io.github.rcneg.alexsmobsdelight.accessor.IEntitySeagullData;
import io.github.rcneg.alexsmobsdelight.config.Config;
import io.github.rcneg.alexsmobsdelight.init.EffectRegistry;
import io.github.rcneg.alexsmobsdelight.init.ItemRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@EventBusSubscriber
public class AttackEvents {

    @SubscribeEvent
    public static void onLivingHurt(LivingIncomingDamageEvent event){
        LivingEntity entity = event.getEntity();
        RandomSource random = entity.getRandom();
        if (event.getEntity().level() instanceof ServerLevel level && event.getSource().getEntity() instanceof LivingEntity attacker) {
            float lootHP = (float) (Config.CROCODILE_KNIFE_HEALTH.get() * 1.0f);
            if(attacker.getMainHandItem().is(ItemRegistry.CROCODILE_KNIFE.get())
                    && !Config.LOOTING_BLACKLIST.contains(entity.getType())){
                if(lootHP * 2.0f * attacker.getMaxHealth() > entity.getMaxHealth()){
                    float p = Mth.clamp((((lootHP * 2.0f * attacker.getMaxHealth()) - entity.getMaxHealth()) / (lootHP * attacker.getMaxHealth())), 0, 1);
                    if(attacker instanceof Player player && p < 1){
                        player.displayClientMessage(Component.translatable("message.alexsmobsdelight.crocodile_knife_1").withStyle(ChatFormatting.GOLD), true);
                    }
                    if(random.nextInt(100) <= (float)Config.CROCODILE_KNIFE_LOOT.get() * p){
                        ResourceKey<LootTable> lootId = entity.getLootTable();
                        LootParams ctx = new LootParams.Builder(level)
                                .withParameter(LootContextParams.THIS_ENTITY, entity)
                                .withParameter(LootContextParams.ORIGIN, entity.position())
                                .withParameter(LootContextParams.DAMAGE_SOURCE, event.getSource())
                                .withOptionalParameter(LootContextParams.ATTACKING_ENTITY, attacker)
                                .create(LootContextParamSets.ENTITY);
                        List<ItemStack> drops = level.getServer().reloadableRegistries().getLootTable(lootId).getRandomItems(ctx);
                        if (!drops.isEmpty()) {
                            if(!Config.CROCODILE_KNIFE_FULL_DROP.get()){
                                ItemStack drop = drops.get(attacker.getRandom().nextInt(drops.size())).copy();
                                drop.setCount(1);
                                entity.spawnAtLocation(drop);
                            }else{
                                for(ItemStack drop : drops){
                                    entity.spawnAtLocation(drop);
                                }
                            }
                        }
                        //Objects.requireNonNull(level.getServer()).getLootData().getLootTable(lootId).getRandomItems(ctx, s -> entity.spawnAtLocation(s, 1.0F));
                        level.playSound((Player)null, entity.getOnPos(), AMSoundRegistry.CROCODILE_HURT.get(), SoundSource.PLAYERS);
                    }
                }else{
                    if(attacker instanceof Player player){
                        player.displayClientMessage(Component.translatable("message.alexsmobsdelight.crocodile_knife").withStyle(ChatFormatting.RED), true);
                    }
                }

            }
            if(attacker.getMainHandItem().is(ItemTags.create(ResourceLocation.parse("alexsmobsdelight:tools/mantis_shrimp_tools")))){
                entity.setRemainingFireTicks(400);
                level.playSound((Player)null, entity.getOnPos(), AMSoundRegistry.MANTIS_SHRIMP_SNAP.get(), SoundSource.PLAYERS);
            }
            if(attacker instanceof Player player && player.hasEffect(EffectRegistry.CROCODILE_DEATH_ROLL) && player.getFoodData().getFoodLevel() > 0){
                player.startAutoSpinAttack(20, 6.0F, player.getMainHandItem());
                level.playSound((Player)null, entity.getOnPos(), AMSoundRegistry.CROCODILE_BITE.get(), SoundSource.PLAYERS);
            }
            if(attacker.hasEffect(EffectRegistry.CROCODILE_SHARPNESS)){
                int amp = attacker.getEffect(EffectRegistry.CROCODILE_SHARPNESS).getAmplifier();
                entity.addEffect(new MobEffectInstance(AMEffectRegistry.EXSANGUINATION, 100, amp));
            }

            if(attacker.hasEffect(EffectRegistry.POISON_FANGS)){
                int amp = attacker.getEffect(EffectRegistry.POISON_FANGS).getAmplifier();
                entity.addEffect(new MobEffectInstance(MobEffects.POISON, 100 * amp, 1));
            }
        }
    }
    @SubscribeEvent
    public static void onEntityLoot(LivingDropsEvent event) {
        if (!event.getEntity().level().isClientSide()) {
            LivingEntity entity = event.getEntity();
            if (entity instanceof IEntitySeagullData seagull){
                if(!seagull.amd$getEffects().isEmpty()){
                    ItemStack meat = seagull.amd$getConsumedEternalFood() ? new ItemStack(ItemRegistry.ENCHANTED_ETERNAL_COOKED_SEAGULL.get()) : new ItemStack(ItemRegistry.ENCHANTED_COOKED_SEAGULL.get());
                    ListTag listtag = new ListTag();
                    for (MobEffectInstance mobeffectinstance : seagull.amd$getEffects().values()) {
                        listtag.add(mobeffectinstance.save());
                    }
                    CustomData.update(DataComponents.CUSTOM_DATA, meat,
                            tag -> tag.put("AmdConsumedFoodEffects", listtag));
                    addEntityDrops(event, meat);
                }else if(seagull.amd$getConsumedEternalFood()){
                    addEntityDrops(event, new ItemStack(ItemRegistry.ETERNAL_COOKED_SEAGULL.get()));
                }
            }

            if (entity instanceof EntityTarantulaHawk hawk){
                if(hawk.isBaby()){
                    if(hawk.isOnFire()){
                        addEntityDrops(event, new ItemStack(ItemRegistry.COOKED_TARANTULA_HAWK_LARVA.get()));
                    }else {
                        addEntityDrops(event, new ItemStack(ItemRegistry.RAW_TARANTULA_HAWK_LARVA.get()));
                    }
                }
            }

            if (event.getEntity().level() instanceof ServerLevel level && event.getSource().getEntity() instanceof LivingEntity attacker) {
                if(attacker.getMainHandItem().is(ItemRegistry.DIMENSIONAL_SLICER.get())){
                    ResourceKey<LootTable> lootId = entity.getLootTable();
                    LootTable lootTable = level.getServer().reloadableRegistries().getLootTable(lootId);
                    List<ItemStack> allDrops = new ArrayList<>();
                    List<Item> actualDrops = new ArrayList<>();
                    LootContext ctx = new LootContext.Builder(
                            new LootParams.Builder(level)
                                    .withParameter(LootContextParams.THIS_ENTITY, entity)
                                    .withParameter(LootContextParams.ORIGIN, entity.position())
                                    .withParameter(LootContextParams.DAMAGE_SOURCE, event.getSource())
                                    .withOptionalParameter(LootContextParams.ATTACKING_ENTITY, attacker)
                                    .create(LootContextParamSets.ENTITY)
                    ).create(Optional.empty());
                    for (LootPool pool : lootTable.pools) {
                        for (LootPoolEntryContainer entry : pool.entries) {
                            if (entry instanceof LootPoolSingletonContainer singleton) {
                                if(singleton instanceof LootItem lootItem){
                                    ItemStack baseStack = new ItemStack(lootItem.item);
                                    for (LootItemFunction function : singleton.functions) {
                                        baseStack = function.apply(baseStack, ctx);
                                    }
                                    if (baseStack.getCount() <= 0) {
                                        baseStack.setCount(1);
                                    }
                                    allDrops.add(baseStack.copy());
                                }

                            }
                        }
                    }
                    for(ItemEntity actualDrop : event.getDrops()){
                        actualDrops.add(actualDrop.getItem().getItem());
                    }
                    for(ItemStack drop : allDrops){
                        drop.setCount(1);
                        if(!actualDrops.contains(drop.getItem())){
                            addEntityDrops(event, drop);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void OnLivingAttackEvent(LivingIncomingDamageEvent event){
        Level level = event.getEntity().level();
        if (!level.isClientSide()) {
            LivingEntity entity = event.getEntity();
            if(entity.hasEffect(EffectRegistry.DODGE) && event.getSource().getDirectEntity() instanceof Projectile){
                if(entity.getRandom().nextInt(10) > Math.pow(0.5, (entity.getEffect(EffectRegistry.DODGE).getAmplifier() + 1))){
                    level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.WOOL_PLACE, SoundSource.NEUTRAL, 1.0F, 0.3F);
                    event.setCanceled(true);
                }
            }
        }
    }

    public static void addEntityDrops(LivingDropsEvent event, ItemStack itemStack) {
        ItemEntity itemEntity = new ItemEntity(event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), itemStack);
        itemEntity.setPickUpDelay(10);
        event.getDrops().add(itemEntity);
    }
}
