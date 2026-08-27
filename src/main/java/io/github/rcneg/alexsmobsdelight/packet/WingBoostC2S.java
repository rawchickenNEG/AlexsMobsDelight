package io.github.rcneg.alexsmobsdelight.packet;

import com.alexsmobsup.misc.AMSoundRegistry;
import io.github.rcneg.alexsmobsdelight.AlexsMobsDelight;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/** Serverbound payload sent when the Fluttering effect triggers a wing boost. */
public final class WingBoostC2S implements CustomPacketPayload {
    public static final Type<WingBoostC2S> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(AlexsMobsDelight.MODID, "wing_boost"));
    public static final StreamCodec<RegistryFriendlyByteBuf, WingBoostC2S> STREAM_CODEC =
            StreamCodec.unit(new WingBoostC2S());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(WingBoostC2S payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer player)) return;
            if (player.getFallFlyingTicks() > 10 && player.getFoodData().getFoodLevel() > 6) {
                player.setDeltaMovement(player.getLookAngle().scale(1.5F));
                player.hurtMarked = true;
                if (!player.getAbilities().instabuild) {
                    player.getFoodData().addExhaustion(4.0F);
                }
                player.level().playSound(null, player, AMSoundRegistry.TARANTULA_HAWK_WING.get(),
                        SoundSource.PLAYERS, 1.0F, 1.0F);
            }
        });
    }
}
