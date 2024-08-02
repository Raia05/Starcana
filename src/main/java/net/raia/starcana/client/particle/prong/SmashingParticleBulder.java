package net.raia.starcana.client.particle.prong;

import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.raia.starcana.client.particle.ParticleBuilderFactory;
import net.raia.starcana.client.particle.StarcanaParticle;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;

import java.awt.*;

public class SmashingParticleBulder implements ParticleBuilderFactory {
    @Override
    public void build(World level, Vec3d pos, Color startingColor, Color endingColor) {
        WorldParticleBuilder.create(StarcanaParticle.CIRCLE)
                .setScaleData(GenericParticleData.create(1f, 5f).build())
                .setTransparencyData(GenericParticleData.create(0.5f).build())
                .setColorData(ColorParticleData.create(startingColor, endingColor).setCoefficient(1.4f).setEasing(Easing.ELASTIC_IN).build())
                .setLifetime(20)
                .addMotion(0, 0, 0)
                .enableNoClip()
                .spawn(level, pos.x, pos.y, pos.z);
    }
}
