package io.github.rcneg.alexsmobsdelight.mixin;

import com.github.alexthe666.alexsmobs.entity.EntityDevilsHolePupfish;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityDevilsHolePupfish.class)
public class EntityDevilsHolePupfishMixin{

    @Inject(method = "spawnBabiesWith", at = @At("TAIL"), remap = false)
    private void amd$breakBlockWhenSpawnBabies(EntityDevilsHolePupfish chasePartner, CallbackInfo ci) {
        Level level = chasePartner.level();
        BlockPos pos = chasePartner.getOnPos().above();
        for(Direction dir : Direction.values()){
            if(level.getBlockState(pos.relative(dir)).is(AMTagRegistry.PUPFISH_EATABLES)){
                level.destroyBlock(pos.relative(dir), true, chasePartner);
                break;
            }
        }

    }
}
