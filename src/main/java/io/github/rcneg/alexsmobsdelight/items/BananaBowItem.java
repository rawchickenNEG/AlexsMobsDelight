package io.github.rcneg.alexsmobsdelight.items;

import com.alexsmobsup.item.AMItemRegistry;
import io.github.rcneg.alexsmobsdelight.entities.ThrownBananaEntity;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.EventHooks;

import java.util.function.Predicate;

public class BananaBowItem extends BowItem {

    public BananaBowItem(Item.Properties p_40660_) {
        super(p_40660_);
    }

    public void releaseUsing(ItemStack p_40667_, Level p_40668_, LivingEntity p_40669_, int p_40670_) {
        if (p_40669_ instanceof Player player) {
            boolean flag = player.getAbilities().instabuild || hasEnchantment(p_40667_, Enchantments.INFINITY);
            ItemStack itemstack = player.getProjectile(p_40667_);
            int i = this.getUseDuration(p_40667_, p_40669_) - p_40670_;
            i = EventHooks.onArrowLoose(p_40667_, p_40668_, player, i, !itemstack.isEmpty() || flag);
            if (i < 0) {
                return;
            }

            if (!itemstack.isEmpty() || flag) {
                if (itemstack.isEmpty()) {
                    itemstack = new ItemStack(AMItemRegistry.BANANA.get());
                }

                float f = getPowerForTime(i);
                if (!((double)f < 0.1)) {
                    if (!p_40668_.isClientSide) {
                        ThrownBananaEntity banana = new ThrownBananaEntity(p_40668_, player);
                        banana.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);

                        if (hasEnchantment(p_40667_, Enchantments.FLAME)) {
                            banana.setRemainingFireTicks(100);
                        }

                        p_40667_.hurtAndBreak(1, player, LivingEntity.getSlotForHand(player.getUsedItemHand()));

                        p_40668_.addFreshEntity(banana);
                    }

                    p_40668_.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (p_40668_.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (!flag && !player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            player.getInventory().removeItem(itemstack);
                        }
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }

    }

    public static float getPowerForTime(int p_40662_) {
        float f = (float)p_40662_ / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public int getUseDuration(ItemStack p_40680_, LivingEntity entity) {
        return 72000;
    }

    public UseAnim getUseAnimation(ItemStack p_40678_) {
        return UseAnim.BOW;
    }

    public InteractionResultHolder<ItemStack> use(Level p_40672_, Player p_40673_, InteractionHand p_40674_) {
        ItemStack itemstack = p_40673_.getItemInHand(p_40674_);
        boolean flag = !p_40673_.getProjectile(itemstack).isEmpty();
        InteractionResultHolder<ItemStack> ret = EventHooks.onArrowNock(itemstack, p_40672_, p_40673_, p_40674_, flag);
        if (ret != null) {
            return ret;
        } else if (!p_40673_.getAbilities().instabuild && !flag) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            p_40673_.startUsingItem(p_40674_);
            return InteractionResultHolder.consume(itemstack);
        }
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return stack -> stack.is(AMItemRegistry.BANANA.get());
    }

    public int getDefaultProjectileRange() {
        return 15;
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return super.supportsEnchantment(stack, enchantment)
                && !enchantment.is(Enchantments.POWER)
                && !enchantment.is(Enchantments.PUNCH);
    }

    private static boolean hasEnchantment(ItemStack stack, ResourceKey<Enchantment> key) {
        return stack.getTagEnchantments().entrySet().stream().anyMatch(entry -> entry.getKey().is(key) && entry.getIntValue() > 0);
    }
}
