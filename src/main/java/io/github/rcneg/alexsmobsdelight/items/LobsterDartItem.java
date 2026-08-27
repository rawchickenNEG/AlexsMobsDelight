package io.github.rcneg.alexsmobsdelight.items;

import io.github.rcneg.alexsmobsdelight.entities.ThrownDartEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class LobsterDartItem extends Item {
    public LobsterDartItem(Item.Properties p_43381_) {
        super(p_43381_.attributes(ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, 5.0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_SPEED, new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, -3.0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .build()));
    }

    public boolean canAttackBlock(BlockState p_43409_, Level p_43410_, BlockPos p_43411_, Player p_43412_) {
        return !p_43412_.isCreative();
    }

    public UseAnim getUseAnimation(ItemStack p_43417_) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack p_43419_, LivingEntity entity) {
        return 72000;
    }

    public void releaseUsing(ItemStack p_43394_, Level p_43395_, LivingEntity p_43396_, int p_43397_) {
        if (p_43396_ instanceof Player $$4) {
            int $$5 = this.getUseDuration(p_43394_, p_43396_) - p_43397_;
            if ($$5 >= 5) {
                if (!p_43395_.isClientSide) {
                    p_43394_.hurtAndBreak(1, $$4, LivingEntity.getSlotForHand($$4.getUsedItemHand()));
                    ThrownDartEntity $$7 = new ThrownDartEntity(p_43395_, $$4, p_43394_);
                    $$7.shootFromRotation($$4, $$4.getXRot(), $$4.getYRot(), 0.0F, 2.5F, 1.2f);
                    if ($$4.getAbilities().instabuild) {
                        $$7.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                    }

                    p_43395_.addFreshEntity($$7);
                    p_43395_.playSound((Player)null, $$7, SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F);
                    if (!$$4.getAbilities().instabuild) {
                        $$4.getInventory().removeItem(p_43394_);
                    }
                }
            }
        }
    }

    public InteractionResultHolder<ItemStack> use(Level p_43405_, Player p_43406_, InteractionHand p_43407_) {
        ItemStack $$3 = p_43406_.getItemInHand(p_43407_);
        if ($$3.getDamageValue() >= $$3.getMaxDamage() - 1) {
            return InteractionResultHolder.fail($$3);
        } else {
            p_43406_.startUsingItem(p_43407_);
            return InteractionResultHolder.consume($$3);
        }
    }

    public boolean hurtEnemy(ItemStack p_43390_, LivingEntity p_43391_, LivingEntity p_43392_) {
        p_43390_.hurtAndBreak(1, p_43392_, EquipmentSlot.MAINHAND);
        return true;
    }

    public boolean mineBlock(ItemStack p_43399_, Level p_43400_, BlockState p_43401_, BlockPos p_43402_, LivingEntity p_43403_) {
        if ((double)p_43401_.getDestroySpeed(p_43400_, p_43402_) != 0.0) {
            p_43399_.hurtAndBreak(2, p_43403_, EquipmentSlot.MAINHAND);
        }
        return true;
    }

    public int getEnchantmentValue() {
        return 1;
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return super.supportsEnchantment(stack, enchantment)
                && !enchantment.is(Enchantments.RIPTIDE)
                && !enchantment.is(Enchantments.CHANNELING);
    }
}
