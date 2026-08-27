package io.github.rcneg.alexsmobsdelight.events;


import io.github.rcneg.alexsmobsdelight.AlexsMobsDelight;
import io.github.rcneg.alexsmobsdelight.init.EffectRegistry;
import io.github.rcneg.alexsmobsdelight.packet.WingBoostC2S;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

@EventBusSubscriber(modid = AlexsMobsDelight.MODID, value = Dist.CLIENT)
public class ClientForgeEvents {

    private static boolean lastBoostPressed = false;

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
            Minecraft mc = Minecraft.getInstance();
            LocalPlayer player = mc.player;

            if (player != null){

                boolean boost = mc.options.keyJump.isDown();
                if(player.isFallFlying() && player.hasEffect(EffectRegistry.FLUTTERING)){
                    if(boost && !lastBoostPressed){
                        net.neoforged.neoforge.network.PacketDistributor.sendToServer(new WingBoostC2S());
                    }
                }
                lastBoostPressed = boost;
            }
    }
}
