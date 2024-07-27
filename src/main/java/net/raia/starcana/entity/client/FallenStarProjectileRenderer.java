package net.raia.starcana.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.raia.starcana.entity.mobs.FallenStarProjectile;
import net.raia.starcana.utils.StarcanaColors;
import team.lodestar.lodestone.registry.common.particle.LodestoneParticleRegistry;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleData;

import java.awt.*;

public class FallenStarProjectileRenderer extends EntityRenderer<FallenStarProjectile> {

    public FallenStarProjectileRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public Identifier getTexture(FallenStarProjectile entity) {
        return null;
    }

    @Override
    public void render(FallenStarProjectile entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {

        WorldParticleBuilder.create(LodestoneParticleRegistry.SMOKE_PARTICLE)
                .setScaleData(GenericParticleData.create((.2f + entity.getWorld().random.nextFloat() / 3f)).build())
                .setColorData(ColorParticleData.create(StarcanaColors.Magenta[0], StarcanaColors.Magenta[8]).build())
                .enableNoClip()
                .setLifetime(20)
                .setSpinData(SpinParticleData.create((float) (entity.getWorld().random.nextGaussian() / 100f)).setSpinOffset(entity.getWorld().random.nextFloat() * 360f).build())
                .spawn(entity.getWorld(), entity.getX(), entity.getY() + entity.getHeight() / 2f, entity.getZ());
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }
}
