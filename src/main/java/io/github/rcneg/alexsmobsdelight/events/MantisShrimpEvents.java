package io.github.rcneg.alexsmobsdelight.events;

import com.alexsmobsup.entity.EntityMantisShrimp;
import com.alexsmobsup.item.AMItemRegistry;
import com.alexsmobsup.misc.AMTagRegistry;
import io.github.rcneg.alexsmobsdelight.helper.ItemStackDataCompat;
import io.github.rcneg.alexsmobsdelight.init.ItemRegistry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber
public class MantisShrimpEvents {
    private static final TagKey<Item> EGGS = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "eggs"));

    @SubscribeEvent
    public static void onMainHandChanged(LivingEquipmentChangeEvent event) {
        if (!(event.getEntity() instanceof EntityMantisShrimp shrimp)
                || event.getSlot() != EquipmentSlot.MAINHAND) {
            return;
        }

        ItemStack previousItem = event.getFrom();
        ItemStack currentItem = event.getTo();
        if (previousItem.is(AMTagRegistry.SHRIMP_RICE_FRYABLES)
                && previousItem.is(EGGS)
                && currentItem.is(AMItemRegistry.SHRIMP_FRIED_RICE.get())) {
            shrimp.setItemInHand(net.minecraft.world.InteractionHand.MAIN_HAND,
                    new ItemStack(ItemRegistry.SHRIMP_FRIED_EGG.get()));
        }
    }

    @SubscribeEvent
    public static void onMantisShrimpTick(EntityTickEvent.Post event) {
        if (!(event.getEntity() instanceof EntityMantisShrimp shrimp) || shrimp.level().isClientSide()) {
            return;
        }

        ItemStack stack = shrimp.getMainHandItem();
        if (!stack.is(ItemRegistry.CROCODILE_KNIFE.get())) {
            return;
        }

        CompoundTag tag = ItemStackDataCompat.read(stack);
        int counter = tag.getInt("AMDCrocodileKnifeCounter");
        if (counter >= 3000) {
            Item tail = switch (shrimp.getVariant()) {
                case 1 -> ItemRegistry.MANTIS_SHRIMP_TAIL_RED.get();
                case 2 -> ItemRegistry.MANTIS_SHRIMP_TAIL_LIME.get();
                case 3 -> ItemRegistry.MANTIS_SHRIMP_TAIL_WHITE.get();
                default -> ItemRegistry.MANTIS_SHRIMP_TAIL_GREEN.get();
            };
            shrimp.spawnAtLocation(new ItemStack(tail));
            tag.putInt("AMDCrocodileKnifeCounter", 0);
        } else {
            tag.putInt("AMDCrocodileKnifeCounter", counter + 1);
        }

        CustomData.update(DataComponents.CUSTOM_DATA, stack, existing -> existing.merge(tag));
    }
}
