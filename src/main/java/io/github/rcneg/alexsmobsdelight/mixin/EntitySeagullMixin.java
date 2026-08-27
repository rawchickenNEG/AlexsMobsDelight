package io.github.rcneg.alexsmobsdelight.mixin;

import com.alexsmobsup.entity.EntitySeagull;
import io.github.rcneg.alexsmobsdelight.helper.EntityHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntitySeagull.class)
public class EntitySeagullMixin {

    @Inject(method = "setTreasurePos", at = @At("TAIL"), remap = false)
    private void amd$setTreasureSand(BlockPos pos, CallbackInfo ci) {
        EntitySeagull seagull = (EntitySeagull) (Object) this;
        Level level = seagull.level();
        Player player = EntityHelper.getClosestPlayer(level, EntityHelper.getVec3(seagull), 16);
        for(int i = 0; i < 128; ++i) {
            BlockPos pos1 = new BlockPos(pos.getX(), i, pos.getZ());
            if (level.getBlockState(pos1).getBlock() instanceof ChestBlock
                    && (level.getBlockState(pos1.below()).is(Blocks.SAND)
                    || level.getBlockState(pos1.below()).is(Blocks.GRAVEL)
                    || level.getBlockState(pos1.below()).is(Blocks.STONE)
                    || level.getBlockState(pos1.below()).is(Blocks.SANDSTONE))) {
                boolean flag = true;
                for(Direction d : Direction.values()){
                    if(!level.getBlockState(pos1.relative(d)).isSolid()){
                        flag = false;
                    }
                }
                if(flag){
                    BlockState treasureSand = level.getBlockState(pos1.below()).is(Blocks.GRAVEL) || level.getBlockState(pos1.below()).is(Blocks.STONE) ? Blocks.SUSPICIOUS_GRAVEL.defaultBlockState() : Blocks.SUSPICIOUS_SAND.defaultBlockState();
                    CompoundTag tag = level.getBlockEntity(pos1).saveWithoutMetadata(level.registryAccess());
                    if(level.getBlockEntity(pos1) instanceof RandomizableContainerBlockEntity chestEntity && tag != null && tag.contains("LootTable", 8)){
                        if(level.setBlock(pos1.below(), treasureSand, 3)){
                            BlockEntity entity = level.getBlockEntity(pos1.below());
                            if(entity instanceof BrushableBlockEntity brushable){
                                if (player != null) {
                                    chestEntity.unpackLootTable(player);
                                }
                                brushable.setLootTable(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath("alexsmobsdelight", "gameplay/seagull_treasure_sand")), tag.getLong("LootTableSeed"));
                                brushable.setChanged();
                                level.sendBlockUpdated(pos1.below(), treasureSand, treasureSand, Block.UPDATE_CLIENTS);
                            }
                        }
                        break;
                    }
                }
            }
        }

    }

}
