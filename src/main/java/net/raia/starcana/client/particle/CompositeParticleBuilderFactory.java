package net.raia.starcana.client.particle;

import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.awt.*;
import java.util.List;

public class CompositeParticleBuilderFactory implements ParticleBuilderFactory{
    private final List<ParticleBuilderFactory> factories;

    public CompositeParticleBuilderFactory(List<ParticleBuilderFactory> factories) {
        this.factories = factories;
    }

    @Override
    public void build(World level, Vec3d pos, Color startingColor, Color endingColor) {
        for (ParticleBuilderFactory factory : factories) {
            factory.build(level, pos, startingColor, endingColor);
        }
    }

}
