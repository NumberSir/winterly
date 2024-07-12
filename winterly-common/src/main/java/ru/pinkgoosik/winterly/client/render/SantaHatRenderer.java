package ru.pinkgoosik.winterly.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import ru.pinkgoosik.winterly.Winterly;
import ru.pinkgoosik.winterly.client.model.WinterlyModels;

public class SantaHatRenderer extends MobDecorationRenderer {
	public final String texture;

	public SantaHatRenderer(String texture) {
		this.texture = texture;
	}

    @Override
    public void render(HumanoidModel<?> contextModel, PoseStack matrices, MultiBufferSource vertexConsumers, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        WinterlyModels.SANTA_HAT_MODEL.hat.copyFrom(contextModel.head);
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderType.entityCutout(Winterly.id("textures/entity/" + texture + ".png")));
        WinterlyModels.SANTA_HAT_MODEL.renderToBuffer(matrices, vertexConsumer, light, OverlayTexture.NO_OVERLAY);
    }
}
