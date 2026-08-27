package io.github.rcneg.alexsmobsdelight.client.renderer.layers;

import com.alexsmobsup.client.model.ModelSeagull;
import com.alexsmobsup.entity.EntitySeagull;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.rcneg.alexsmobsdelight.entities.SuperSeagull;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SuperSeagullArmorLayer extends RenderLayer<EntitySeagull, ModelSeagull> {

    private static final ResourceLocation ARMOR_TEXTURE = ResourceLocation.withDefaultNamespace("textures/entity/wither/wither_armor.png");

    public SuperSeagullArmorLayer(RenderLayerParent<EntitySeagull, ModelSeagull> parent) {
        super(parent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, EntitySeagull entity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {

        if (entity instanceof SuperSeagull superSeagull && superSeagull.isPowered()) {
            float tick = (float) entity.tickCount + partialTick;
            EntityModel<EntitySeagull> model = this.getParentModel();
            model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTick);
            this.getParentModel().copyPropertiesTo(model);

            VertexConsumer vertexConsumer = bufferSource.getBuffer(
                    RenderType.energySwirl(ARMOR_TEXTURE, Mth.cos(tick * 0.02F) * 3.0F % 1.0F, tick * 0.01F % 1.0F)
            );

            model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            float color1 = superSeagull.getHealth() * 2 > superSeagull.getMaxHealth() ? 0.5F : 1.0F;
            float color2 = superSeagull.getHealth() * 2 > superSeagull.getMaxHealth() ? 1.0F : 0.5F;
            model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY,
                    FastColor.ARGB32.colorFromFloat(0.5F, color1, 0.5F, color2));
        }
  }
}