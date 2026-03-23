package io.github.rcneg.alexsmobsdelight.mixin;

import com.github.alexthe666.alexsmobs.entity.EntityRaccoon;
import io.github.rcneg.alexsmobsdelight.init.ItemRegistry;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRaccoon.class)
public class EntityRaccoonMixin {
    @Inject(
            method = "isLootable",
            at = @At("HEAD"),
            remap = false,
            cancellable = true)

    private void amd$stopBeLootable(Container inventory, CallbackInfoReturnable<Boolean> cir) {
        for(int i = 0; i < inventory.getContainerSize(); ++i) {
            ItemStack stack = inventory.getItem(i);
            if (stack.getItem() == ItemRegistry.OPENED_SURFLYGFISK.get()) {
                cir.setReturnValue(false);
            }
        }
    }
}
