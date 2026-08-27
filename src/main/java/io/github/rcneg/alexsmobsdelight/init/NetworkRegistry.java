package io.github.rcneg.alexsmobsdelight.init;

import io.github.rcneg.alexsmobsdelight.packet.WingBoostC2S;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

public final class NetworkRegistry {
    private NetworkRegistry() {
    }

    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {
        event.registrar("1").playToServer(WingBoostC2S.TYPE, WingBoostC2S.STREAM_CODEC, WingBoostC2S::handle);
    }
}
