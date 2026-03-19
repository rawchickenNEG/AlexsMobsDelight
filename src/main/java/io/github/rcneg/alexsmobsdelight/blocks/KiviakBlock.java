package io.github.rcneg.alexsmobsdelight.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import vectorwing.farmersdelight.common.tag.ModTags;

public class KiviakBlock extends Block {
    public static IntegerProperty COMPOSTING = IntegerProperty.create("composting", 0, 7);
    public static final DirectionProperty FACING;
    public static BooleanProperty OPENED = BooleanProperty.create("opened");

    public KiviakBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(super.defaultBlockState().setValue(COMPOSTING, 0).setValue(FACING, Direction.NORTH).setValue(OPENED, false));
    }

    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(COMPOSTING, FACING, OPENED);
        super.createBlockStateDefinition(builder);
    }

    public int getMaxCompostingStage() {
        return 7;
    }

    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isClientSide) {
            float chance = 0.0F;
            boolean coldBiome = false;
            int maxLight = 0;

            for (BlockPos neighborPos : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, 1, 1))) {
                BlockState neighborState = level.getBlockState(neighborPos);
                if (neighborState.is(BlockTags.ICE)) {
                    chance += 0.02F;
                }

                if (level.getBiome(pos).is(BiomeTags.SPAWNS_SNOW_FOXES) || level.getBiome(pos).is(BiomeTags.SPAWNS_COLD_VARIANT_FROGS)) {
                    coldBiome = true;
                }

                int light = level.getBrightness(LightLayer.SKY, neighborPos.above());
                if (light > maxLight) {
                    maxLight = light;
                }
            }

            chance += maxLight < 5 ? 0.1F : 0.05F;
            chance += coldBiome ? 0.1F : 0.0F;
            if (level.getRandom().nextFloat() <= chance) {
                if ((Integer)state.getValue(COMPOSTING) != this.getMaxCompostingStage()) {
                    level.setBlock(pos, (BlockState) state.setValue(COMPOSTING, (Integer) state.getValue(COMPOSTING) + 1), 3);
                }
            }

        }
    }

    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos pos) {
        return this.getMaxCompostingStage() + 1 - blockState.getValue(COMPOSTING);
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);
        if (random.nextInt(100) == state.getValue(COMPOSTING) * 10) {
            level.addParticle(ParticleTypes.MYCELIUM, (double)pos.getX() + (double)random.nextFloat(), (double)pos.getY() + 1.1, (double)pos.getZ() + (double)random.nextFloat(), 0.0, 0.0, 0.0);
        }
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        return level.isClientSide && this.takeServing(level, pos, state, player, hand).consumesAction() ? InteractionResult.SUCCESS : this.takeServing(level, pos, state, player, hand);
    }

    protected InteractionResult takeServing(LevelAccessor level, BlockPos pos, BlockState state, Player player, InteractionHand hand) {
        int shouldOpen = state.getValue(COMPOSTING);
        boolean opened = state.getValue(OPENED);
        if (shouldOpen == 7 && player.getItemInHand(hand).is(ModTags.KNIVES) && !opened) {
            level.playSound((Player)null, pos, SoundEvents.SHEEP_SHEAR, SoundSource.PLAYERS, 0.8F, 0.8F);
            level.setBlock(pos, (BlockState) state.setValue(OPENED, true), 3);
            return InteractionResult.SUCCESS;
        } else {
            player.displayClientMessage(Component.translatable("alexsmobsdelight.jei.kiviak_decomposition.tips"), true);
            return InteractionResult.PASS;
        }
    }

    static {
        FACING = BlockStateProperties.HORIZONTAL_FACING;
   }
}