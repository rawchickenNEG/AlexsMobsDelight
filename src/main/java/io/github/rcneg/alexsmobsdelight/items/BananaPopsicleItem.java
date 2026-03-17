package io.github.rcneg.alexsmobsdelight.items;

import com.github.alexthe666.alexsmobs.block.AMBlockRegistry;
import com.github.alexthe666.alexsmobs.block.BlockBananaSlugSlime;
import com.github.alexthe666.alexsmobs.entity.util.RainbowUtil;
import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import vectorwing.farmersdelight.common.item.PopsicleItem;

import java.util.Queue;

public class BananaPopsicleItem extends PopsicleItem {
    public BananaPopsicleItem(Properties properties) {
        super(properties);
    }

    public ItemStack finishUsingItem(ItemStack st, Level level, LivingEntity e) {
        removeWaterBreadthFirstSearch(level, e.getOnPos());
        return super.finishUsingItem(st, level, e);
    }

    private boolean removeWaterBreadthFirstSearch(Level level, BlockPos pos) {
        Queue<Tuple<BlockPos, Integer>> queue = Lists.newLinkedList();
        queue.add(new Tuple(pos, 0));
        int i = 0;
        int fullBlocks = 0;
        FluidState lastFluidState = null;

        while(!queue.isEmpty()) {
            Tuple<BlockPos, Integer> tuple = (Tuple)queue.poll();
            BlockPos blockpos = (BlockPos)tuple.getA();
            BlockState state = level.getBlockState(blockpos);
            int j = (Integer)tuple.getB();
            if (!state.getFluidState().isEmpty()) {
                ++fullBlocks;
                if (state.getBlock() instanceof BucketPickup) {
                    ((BucketPickup)state.getBlock()).pickupBlock(level, blockpos, state);
                    if (level.getBlockState(blockpos).isAir()) {
                        level.setBlockAndUpdate(blockpos, ((Block) AMBlockRegistry.CRYSTALIZED_BANANA_SLUG_MUCUS.get()).defaultBlockState());
                    }
                } else {
                    level.setBlockAndUpdate(blockpos, ((Block)AMBlockRegistry.CRYSTALIZED_BANANA_SLUG_MUCUS.get()).defaultBlockState());
                }
            }

            Direction[] var11 = Direction.values();
            int var12 = var11.length;

            for(int var13 = 0; var13 < var12; ++var13) {
                Direction direction = var11[var13];
                BlockPos blockpos1 = blockpos.relative(direction);
                BlockState blockstate = level.getBlockState(blockpos1);
                FluidState fluidstate = level.getFluidState(blockpos1);
                if (lastFluidState == null || fluidstate.isEmpty() || lastFluidState.getFluidType() == fluidstate.getFluidType()) {
                    if (blockstate.getBlock() instanceof SimpleWaterloggedBlock) {
                        if (!fluidstate.isEmpty()) {
                            lastFluidState = fluidstate;
                        }

                        ++i;
                        ++fullBlocks;
                        level.setBlockAndUpdate(blockpos1, (BlockState)blockstate.setValue(BlockStateProperties.WATERLOGGED, false));
                        if (j < 6) {
                            queue.add(new Tuple(blockpos1, j + 1));
                        }
                    } else if (blockstate.getBlock() instanceof BucketPickup) {
                        if (!fluidstate.isEmpty()) {
                            lastFluidState = fluidstate;
                        }

                        ++i;
                        ++fullBlocks;
                        ((BucketPickup)blockstate.getBlock()).pickupBlock(level, blockpos1, blockstate);
                        if (level.getBlockState(blockpos).isAir()) {
                            level.setBlockAndUpdate(blockpos, ((Block)AMBlockRegistry.CRYSTALIZED_BANANA_SLUG_MUCUS.get()).defaultBlockState());
                        }

                        if (j < 6) {
                            queue.add(new Tuple(blockpos1, j + 1));
                        }
                    } else if (blockstate.getBlock() instanceof LiquidBlock) {
                        if (!fluidstate.isEmpty()) {
                            lastFluidState = fluidstate;
                        }

                        level.setBlockAndUpdate(blockpos1, ((Block)AMBlockRegistry.CRYSTALIZED_BANANA_SLUG_MUCUS.get()).defaultBlockState());
                        ++i;
                        if (blockstate.getFluidState().isSource()) {
                            ++fullBlocks;
                        }

                        if (j < 6) {
                            queue.add(new Tuple(blockpos1, j + 1));
                        }
                    }
                }
            }

            if (i > 64) {
                break;
            }
        }

        return fullBlocks > 0;
    }
}
