package net.raia.starcana.entity.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.random.Random;
import net.raia.starcana.client.StarBeam;
import net.raia.starcana.effect.StarcanaEffects;
import net.raia.starcana.entity.mobs.FallenStarEntity;
import net.raia.starcana.utils.StarcanaColors;
import org.joml.Matrix4f;
import team.lodestar.lodestone.registry.common.particle.LodestoneParticleRegistry;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleData;
import team.lodestar.lodestone.systems.rendering.VFXBuilders;

public class FallenStarRenderer extends EntityRenderer<FallenStarEntity> {
    private static final float HALF_SQRT_3 = (float)(Math.sqrt(3.0) / 2.0);
    VFXBuilders.WorldVFXBuilder builder;
    public FallenStarRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public void render(FallenStarEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        Random random = Random.create(432L);
        VertexConsumer vertexConsumer4 = vertexConsumers.getBuffer(RenderLayer.getLightning());
        matrices.push();
        long time = entity.getWorld().getTime();
        float globalRotation = (time % 3600L) / 5.0F;

        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(globalRotation));
        float l = (16 + tickDelta) / 200.0F;
        float m = Math.min(l > 0.8F ? (l - 0.8F) / 0.2F : 0.0F, 1.0F);
        int totalRays = 8;
        float angleIncrement = 360.0F / totalRays;

        for (int n = 0; n < totalRays; n++) {
            matrices.push();

            float rayRotation = (time % 3600L) / 5.0F;
            float rayAngle = n * angleIncrement;
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rayAngle));
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(rayAngle));

            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(rayRotation));
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(rayRotation));
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rayRotation));


            float radius = random.nextFloat() * 2.5F + 2.5F + m * 2.0F;
            float width = 2F;
            Matrix4f matrix4f = matrices.peek().getPositionMatrix();
            int alpha = (int) (255.0F * (1.0F - m));
            putDeathLightSourceVertex(vertexConsumer4, matrix4f, alpha);
            putDeathLightNegativeXTerminalVertex(vertexConsumer4, matrix4f, radius, width);
            putDeathLightPositiveXTerminalVertex(vertexConsumer4, matrix4f, radius, width);
            putDeathLightSourceVertex(vertexConsumer4, matrix4f, alpha);
            putDeathLightPositiveXTerminalVertex(vertexConsumer4, matrix4f, radius, width);
            putDeathLightPositiveZTerminalVertex(vertexConsumer4, matrix4f, radius, width);
            putDeathLightSourceVertex(vertexConsumer4, matrix4f, alpha);
            putDeathLightPositiveZTerminalVertex(vertexConsumer4, matrix4f, radius, width);
            putDeathLightNegativeXTerminalVertex(vertexConsumer4, matrix4f, radius, width);

            matrices.pop();
        }
        matrices.pop();
        WorldParticleBuilder.create(LodestoneParticleRegistry.SPARK_PARTICLE)
                .setScaleData(GenericParticleData.create((1f + entity.getWorld().random.nextFloat() / 3f)).build())
                .setColorData(ColorParticleData.create(StarcanaColors.Magenta[2], StarcanaColors.Magenta[6]).build())
                .setLifetime(10)
                .setSpinData(SpinParticleData.create((float) (entity.getWorld().random.nextGaussian() / 10000f)).setSpinOffset(entity.getWorld().random.nextFloat() * 360f).build())
                .spawn(entity.getWorld(), entity.getX(), entity.getY(), entity.getZ());

        assert MinecraftClient.getInstance().player != null;
        if (MinecraftClient.getInstance().player.hasStatusEffect(StarcanaEffects.STAR_SEARCHER))
        {
            matrices.push();
            StarBeam.renderBeam(matrices, 0.5f, 0f, 0.5f, 1, entity);
            matrices.pop();
        }


        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    private static void putDeathLightSourceVertex(VertexConsumer buffer, Matrix4f matrix, int alpha) {
        buffer.vertex(matrix, 0.0F, 0.0F, 0.0F).color(242, 205, 206, alpha).next();
    }

    private static void putDeathLightNegativeXTerminalVertex(VertexConsumer buffer, Matrix4f matrix, float radius, float width) {
        buffer.vertex(matrix, -HALF_SQRT_3 * width, radius, -0.5F * width).color(198, 71, 130, 0).next();
    }

    private static void putDeathLightPositiveXTerminalVertex(VertexConsumer buffer, Matrix4f matrix, float radius, float width) {
        buffer.vertex(matrix, HALF_SQRT_3 * width, radius, -0.5F * width).color(198, 71, 130, 0).next();
    }

    private static void putDeathLightPositiveZTerminalVertex(VertexConsumer buffer, Matrix4f matrix, float radius, float width) {
        buffer.vertex(matrix, 0.0F, radius, 1.0F * width).color(198, 71, 130, 0).next();
    }

    @Override
    public Identifier getTexture(FallenStarEntity entity) {
        return null;
    }

    @Override
    public boolean shouldRender(FallenStarEntity entity, Frustum frustum, double x, double y, double z) {
        return true;
    }
}
