package io.github.rcneg.alexsmobsdelight.items;

import com.github.alexthe666.alexsmobs.AlexsMobs;
import io.github.rcneg.alexsmobsdelight.client.renderer.AMDItemstackRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class DimensionalFoods extends Item implements IClientItemExtensions {
    private BlockEntityWithoutLevelRenderer BEWLR;

    public DimensionalFoods(Properties p_41383_) {
        super(p_41383_);
    }
    @OnlyIn(Dist.CLIENT)
    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (BEWLR == null) {
                    BEWLR = new AMDItemstackRenderer();
                }
                return BEWLR;
            }
        });
    }
}
