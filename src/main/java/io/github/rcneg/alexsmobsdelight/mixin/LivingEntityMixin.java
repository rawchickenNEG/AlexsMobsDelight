package io.github.rcneg.alexsmobsdelight.mixin;

import io.github.rcneg.alexsmobsdelight.init.EffectRegistry;
import io.github.rcneg.alexsmobsdelight.init.ItemRegistry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(
            method = "checkAutoSpinAttack",
            at = @At("HEAD"),
            cancellable = true)

    private void amd$spinningWithoutAttackCheck(AABB p_21072_, AABB p_21073_, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (entity.hasEffect(EffectRegistry.CROCODILE_DEATH_ROLL.get())){
            AABB aabb = p_21072_.minmax(p_21073_);
            List<Entity> list = entity.level().getEntities(entity, aabb);
            if (!list.isEmpty()) {
                for(int i = 0; i < list.size(); ++i) {
                    Entity target = (Entity)list.get(i);
                    if (target instanceof LivingEntity) {
                        ci.cancel();
                        break;
                    }
                }
            }
        }
    }
}
