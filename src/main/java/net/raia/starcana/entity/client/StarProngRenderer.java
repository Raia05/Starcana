package net.raia.starcana.entity.client;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;

import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.raia.starcana.Starcana;
import net.raia.starcana.entity.mobs.StarProngEntity;

public class StarProngRenderer extends EntityRenderer<StarProngEntity> {

    private  static  final Identifier TEXTURE = new Identifier(Starcana.MOD_ID, "textures/entity/star_prong.png");
    private final StarProngModel model;

    public StarProngRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new StarProngModel(ctx.getPart(ModModelLayers.STAR_PRONG));

    }

    @Override
    public void render(StarProngEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevYaw, entity.getYaw()) - 90.0F));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevPitch, entity.getPitch()) + 90.0F));
        VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(
                vertexConsumers, this.model.getLayer(this.getTexture(entity)), false, entity.isEnchanted()
        );
        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(StarProngEntity entity) {
        return TEXTURE;
    }

}
