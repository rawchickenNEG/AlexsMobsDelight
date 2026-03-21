package io.github.rcneg.alexsmobsdelight.effects;

import com.github.alexthe666.alexsmobs.block.AMBlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.FrostedIceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.Iterator;

public class CrystallizeWalkerEffect extends MobEffect {
    public CrystallizeWalkerEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    public void applyEffectTick(LivingEntity entity, int amplifier) {
    }

    public static void onEntityMoved(LivingEntity p_45019_, Level p_45020_, BlockPos p_45021_, int p_45022_) {
        if (p_45019_.onGround()) {
            BlockState blockstate = AMBlockRegistry.CRYSTALIZED_BANANA_SLUG_MUCUS.get().defaultBlockState();
            int i = Math.min(16, 2 + p_45022_);
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
            Iterator var7 = BlockPos.betweenClosed(p_45021_.offset(-i, -1, -i), p_45021_.offset(i, -1, i)).iterator();

            while(var7.hasNext()) {
                BlockPos blockpos = (BlockPos)var7.next();
                if (blockpos.closerToCenterThan(p_45019_.position(), (double)i)) {
                    blockpos$mutableblockpos.set(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                    BlockState blockstate1 = p_45020_.getBlockState(blockpos$mutableblockpos);
                    if (blockstate1.isAir()) {
                        BlockState blockstate2 = p_45020_.getBlockState(blockpos);
                        if (blockstate2.getBlock() instanceof BucketPickup && blockstate.canSurvive(p_45020_, blockpos) && p_45020_.isUnobstructed(blockstate, blockpos, CollisionContext.empty()) && !ForgeEventFactory.onBlockPlace(p_45019_, BlockSnapshot.create(p_45020_.dimension(), p_45020_, blockpos), Direction.UP)) {
                            p_45020_.setBlockAndUpdate(blockpos, blockstate);
                            p_45020_.scheduleTick(blockpos, AMBlockRegistry.CRYSTALIZED_BANANA_SLUG_MUCUS.get(), Mth.nextInt(p_45019_.getRandom(), 60, 120));
                        }
                    }
                }
            }
        }

    }

    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration > 0;
    }
}