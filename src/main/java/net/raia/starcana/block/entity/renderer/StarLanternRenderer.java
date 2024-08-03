package net.raia.starcana.block.entity.renderer;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.raia.starcana.block.entity.StarLanternEntity;
import net.raia.starcana.utils.StarcanaColors;
import org.joml.Matrix4f;
import team.lodestar.lodestone.registry.common.particle.LodestoneParticleRegistry;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleData;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleDataBuilder;

import java.util.Objects;


public class StarLanternRenderer implements BlockEntityRenderer<StarLanternEntity> {
    private static final float HALF_SQRT_3 = (float)(Math.sqrt(3.0) / 2.0);
    public StarLanternRenderer(BlockEntityRendererFactory.Context context){}

    @Override
    public void render(StarLanternEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        Vec3d blockPosMiddle = Vec3d.ofCenter(entity.getPos());
        final SpinParticleDataBuilder spinDataBuilder = SpinParticleData.create(0, 1).setSpinOffset(0.025f * Objects.requireNonNull(entity.getWorld()).getTime() % 6.28f).setEasing(Easing.EXPO_IN_OUT);
        WorldParticleBuilder.create(LodestoneParticleRegistry.TWINKLE_PARTICLE)
                .setScaleData(GenericParticleData.create(0.5f).build())
                .setColorData(ColorParticleData.create(StarcanaColors.Magenta[3], StarcanaColors.Magenta[6]).build())
                .setLifetime(1)
                .setSpinData(spinDataBuilder.build())
                .spawn(entity.getWorld(), blockPosMiddle.getX(), blockPosMiddle.getY(), blockPosMiddle.getZ());

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
        buffer.vertex(matrix, 0.0F, radius, width).color(198, 71, 130, 0).next();
    }
}
