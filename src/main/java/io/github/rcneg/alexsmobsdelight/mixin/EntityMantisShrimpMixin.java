package io.github.rcneg.alexsmobsdelight.mixin;

import com.github.alexthe666.alexsmobs.entity.EntityMantisShrimp;
import com.github.alexthe666.alexsmobs.item.AMItemRegistry;
import com.github.alexthe666.alexsmobs.misc.AMTagRegistry;
import io.github.rcneg.alexsmobsdelight.accessor.IEntityMantisShrimpData;
import io.github.rcneg.alexsmobsdelight.init.ItemRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityMantisShrimp.class)
public class EntityMantisShrimpMixin implements IEntityMantisShrimpData {

    @Unique
    private ItemStack amd$lastHoldItem = ItemStack.EMPTY;

    @Override
    public ItemStack amd$getLastHoldItem() {
        return amd$lastHoldItem;
    }

    @Override
    public void amd$setLastHoldItem(ItemStack item) {
        amd$lastHoldItem = item;
    }

    @Inject(
            method = "tick",
            at = @At("TAIL")
    )
    private void amd$CheckLastHoldItem(CallbackInfo ci) {
        EntityMantisShrimp shrimp = (EntityMantisShrimp) (Object) this;
        if(amd$lastHoldItem.is(AMTagRegistry.SHRIMP_RICE_FRYABLES) && amd$lastHoldItem.is(ItemTags.create(new ResourceLocation("forge:eggs"))) && shrimp.getMainHandItem().is(AMItemRegistry.SHRIMP_FRIED_RICE.get())){
            shrimp.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(ItemRegistry.SHRIMP_FRIED_EGG.get()));
        }
        if(amd$lastHoldItem != shrimp.getMainHandItem()){
            amd$lastHoldItem = shrimp.getMainHandItem();
        }
        if(shrimp.getMainHandItem().is(ItemRegistry.CROCODILE_KNIFE.get())){
            ItemStack stack = shrimp.getMainHandItem();
            CompoundTag tag = stack.getOrCreateTag();
            int counter = tag.getInt("AMDCrocodileKnifeCounter");
            if(counter >= 3000){
                Item tail;
                switch (shrimp.getVariant()) {
                    case 1 -> tail = ItemRegistry.MANTIS_SHRIMP_TAIL_RED.get();
                    case 2 -> tail = ItemRegistry.MANTIS_SHRIMP_TAIL_LIME.get();
                    case 3 -> tail = ItemRegistry.MANTIS_SHRIMP_TAIL_WHITE.get();
                    default -> tail = ItemRegistry.MANTIS_SHRIMP_TAIL_GREEN.get();
                }
                shrimp.spawnAtLocation(new ItemStack(tail));
                tag.putInt("AMDCrocodileKnifeCounter", 0);
            }else{
                tag.putInt("AMDCrocodileKnifeCounter", counter + 1);
            }
        }
    }
}
