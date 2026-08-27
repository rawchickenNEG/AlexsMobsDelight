package io.github.rcneg.alexsmobsdelight.client.renderer;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

@OnlyIn(Dist.CLIENT)
public class DimensionalItemRendererProvider implements IClientItemExtensions {

    private BlockEntityWithoutLevelRenderer BEWLR;

    @Override
    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
        if (BEWLR == null) {
            BEWLR = new AMDItemstackRenderer();
        }
        return BEWLR;
    }
}