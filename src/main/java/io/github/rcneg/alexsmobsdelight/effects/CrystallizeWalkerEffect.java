package io.github.rcneg.alexsmobsdelight.effects;

import com.github.alexthe666.alexsmobs.block.AMBlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.ForgeEventFactory;

public class CrystallizeWalkerEffect extends MobEffect {
    public CrystallizeWalkerEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    public void applyEffectTick(LivingEntity entity, int amplifier) {
        Level level = entity.level();
        if (entity.onGround() && !level.isClientSide()) {
            BlockPos pos = entity.getOnPos();
            BlockState blockstate = AMBlockRegistry.CRYSTALIZED_BANANA_SLUG_MUCUS.get().defaultBlockState();
            int i = Math.min(8, 1 + amplifier);
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for (BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-i, -1, -i), pos.offset(i, -1, i))) {
                if (blockpos.closerToCenterThan(entity.position(), (double) i)) {
                    blockpos$mutableblockpos.set(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                    BlockState blockstate1 = level.getBlockState(blockpos$mutableblockpos);
                    if (blockstate1.isAir()) {
                        if (blockstate.canSurvive(level, blockpos) && level.isUnobstructed(blockstate, blockpos, CollisionContext.empty()) && !ForgeEventFactory.onBlockPlace(entity, BlockSnapshot.create(level.dimension(), level, blockpos), Direction.UP)) {
                            level.setBlockAndUpdate(blockpos, blockstate);
                            level.scheduleTick(blockpos, AMBlockRegistry.CRYSTALIZED_BANANA_SLUG_MUCUS.get(), Mth.nextInt(entity.getRandom(), 60, 120));
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