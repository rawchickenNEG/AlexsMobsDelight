package io.github.rcneg.alexsmobsdelight.events;

import io.github.rcneg.alexsmobsdelight.init.ItemRegistry;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public class TradeEvents {
    @SubscribeEvent
    public static void addVillagerTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.FISHERMAN) {
            List<VillagerTrades.ItemListing> trade3 = event.getTrades().get(3);
            List<VillagerTrades.ItemListing> trade5 = event.getTrades().get(5);
            trade3.add(new BasicItemListing(
                    new ItemStack(Items.EMERALD, 8),
                    ItemStack.EMPTY,
                    new ItemStack(ItemRegistry.MANTIS_SHRIMP_TAIL_LIME.get(), 1),
                    12,
                    10,
                    0.2f
            ));
            trade3.add(new BasicItemListing(
                    new ItemStack(Items.EMERALD, 8),
                    ItemStack.EMPTY,
                    new ItemStack(ItemRegistry.MANTIS_SHRIMP_TAIL_RED.get(), 1),
                    12,
                    10,
                    0.2f
            ));
            trade3.add(new BasicItemListing(
                    new ItemStack(Items.EMERALD, 8),
                    ItemStack.EMPTY,
                    new ItemStack(ItemRegistry.MANTIS_SHRIMP_TAIL_GREEN.get(), 1),
                    12,
                    10,
                    0.2f
            ));
            trade3.add(new BasicItemListing(
                    new ItemStack(Items.EMERALD, 8),
                    ItemStack.EMPTY,
                    new ItemStack(ItemRegistry.MANTIS_SHRIMP_TAIL_WHITE.get(), 1),
                    12,
                    10,
                    0.2f
            ));
            trade5.add(new BasicItemListing(
                    new ItemStack(Items.EMERALD, 24),
                    ItemStack.EMPTY,
                    new ItemStack(ItemRegistry.MANTIS_SHRIMP_SHOVEL.get(), 1),
                    3,
                    30,
                    0.2f
            ));
            trade5.add(new BasicItemListing(
                    new ItemStack(Items.EMERALD, 24),
                    ItemStack.EMPTY,
                    new ItemStack(ItemRegistry.MANTIS_SHRIMP_SCYTHE.get(), 1),
                    3,
                    30,
                    0.2f
            ));
            trade5.add(new BasicItemListing(
                    new ItemStack(Items.EMERALD, 24),
                    ItemStack.EMPTY,
                    new ItemStack(ItemRegistry.MANTIS_SHRIMP_HAMMER.get(), 1),
                    3,
                    30,
                    0.2f
            ));
            trade5.add(new BasicItemListing(
                    new ItemStack(Items.EMERALD, 24),
                    ItemStack.EMPTY,
                    new ItemStack(ItemRegistry.MANTIS_SHRIMP_AXE.get(), 1),
                    3,
                    30,
                    0.2f
            ));
        }



    }
}
