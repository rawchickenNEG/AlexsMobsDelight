package io.github.rcneg.alexsmobsdelight.items;

import com.github.alexthe666.alexsmobs.client.particle.AMParticleRegistry;
import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.entity.IHurtableMultipart;
import com.github.alexthe666.alexsmobs.misc.AMAdvancementTriggerRegistry;
import io.github.rcneg.alexsmobsdelight.config.Config;
import io.github.rcneg.alexsmobsdelight.helper.ItemHelper;
import io.github.rcneg.alexsmobsdelight.init.ItemRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FlyingFishCan extends Item {
    public FlyingFishCan(Properties p_41383_) {
        super(p_41383_);
    }

    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        ItemStack itemstack = p_41433_.getItemInHand(p_41434_);
        p_41433_.startUsingItem(p_41434_);
        return InteractionResultHolder.consume(itemstack);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack result = new ItemStack(ItemRegistry.OPENED_SURFLYGFISK.get());

        entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 300, 0));
        level.playSound(null, entity, SoundEvents.DRAGON_FIREBALL_EXPLODE, SoundSource.PLAYERS, 1.0f, 1.5f);

        for(int i = 0; i < 10 + entity.getRandom().nextInt(6); ++i) {
            level.addParticle(AMParticleRegistry.SMELLY.get(), entity.getRandomX(1.0), entity.getRandomY(), entity.getRandomZ(1.0), 0.0, 0.0, 0.0);
        }
        for (Mob nearby : level.getEntitiesOfClass(Mob.class, entity.getBoundingBox().inflate(15.0))) {
            if (nearby != entity && nearby.getId() != entity.getId() && !nearby.getUUID().equals(entity.getUUID()) && !nearby.isAlliedTo(entity) && !entity.isAlliedTo(nearby) && !(entity instanceof IHurtableMultipart)) {
                nearby.setLastHurtByMob(entity);
                nearby.setTarget(entity);
            }
        }
        return result;
    }

    public int getUseDuration(ItemStack p_41454_) {
        return 32;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_40678_) {
        return UseAnim.BOW;
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
    {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(Component.translatable("tooltip.alexsmobsdelight.surflygfisk").withStyle(ChatFormatting.RED));
    }
}
