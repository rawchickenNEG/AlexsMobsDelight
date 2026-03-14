package io.github.rcneg.alexsmobsdelight.items;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vectorwing.farmersdelight.common.item.KnifeItem;

import javax.annotation.Nullable;
import java.util.List;

public class LobsterKnife extends KnifeItem {
    public LobsterKnife(Tier tier, float attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level p_40668_, LivingEntity p_40669_, int p_40670_) {
        int tick = this.getUseDuration(stack) - p_40670_;
        if (p_40669_ instanceof Player player && tick > 20) {
            CompoundTag tag = stack.getOrCreateTag();
            tag.putBoolean("AMDShrimpJump", false);
            tag.putBoolean("AMDShrimpDash", true);
            player.setDeltaMovement(player.getDeltaMovement().add(player.getViewVector(0.7f)));
            player.hurtMarked = true;
            player.getCooldowns().addCooldown(this, 10);
            player.awardStat(Stats.ITEM_USED.get(this));
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_40672_, Player p_40673_, InteractionHand p_40674_) {
        ItemStack itemstack = p_40673_.getItemInHand(p_40674_);
        p_40673_.startUsingItem(p_40674_);
        return InteractionResultHolder.success(itemstack);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);
        if(!level.isClientSide() && entity instanceof Player player) {
            CompoundTag tag = stack.getOrCreateTag();
            if(!tag.getBoolean("AMDShrimpJump") && tag.getBoolean("AMDShrimpDash")){
                AABB box = new AABB(player.getBoundingBox().getCenter().add(player.getViewVector(1)), player.getBoundingBox().getCenter().add(player.getViewVector(1))).inflate(0.1);
                List<Entity> hits = level.getEntities(player, box, e -> e instanceof LivingEntity && e != player);
                if (!hits.isEmpty()) {
                    LivingEntity target = (LivingEntity) hits.get(0);
                    if(target.hurt(player.damageSources().playerAttack(player), 4)){
                        if(target.isAlive()){
                            tag.putBoolean("AMDShrimpJump", true);
                            tag.putBoolean("AMDShrimpDash", false);
                        }
                    }
                }
            }
            if(!player.getCooldowns().isOnCooldown(this) && player.onGround()){
                if(tag.getBoolean("AMDShrimpJump")){
                    player.setDeltaMovement(new Vec3(0, 1.2, 0));
                    player.hurtMarked = true;
                }
                tag.putBoolean("AMDShrimpJump", false);
                tag.putBoolean("AMDShrimpDash", false);
            }
        }
    }

    @Override
    public int getUseDuration(ItemStack p_40680_) {
        return 72000;
    }

    @Override
    public boolean canContinueUsing(ItemStack oldStack, ItemStack newStack) {
        return oldStack.getItem() == newStack.getItem();
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return slotChanged || newStack.getItem() != oldStack.getItem();
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_40678_) {
        return UseAnim.BOW;
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
    {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(Component.translatable("tooltip.alexsmobsdelight.lobster_knife").withStyle(ChatFormatting.GREEN));
    }
}
