package net.raia.starcana.client.network.packets;

import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.raia.starcana.client.particle.DefaultParticleBuilderFactory;
import net.raia.starcana.client.particle.ParticleBuilderFactory;


import java.awt.*;
public class ParticlePacket {
    private final Vec3d position;
    private final int startingColor;
    private final int endingColor;
    private final ParticleBuilderFactory particleBuilderFactory;


    public ParticlePacket(Vec3d position, int startingColor, int endingColor, ParticleBuilderFactory particleBuilderFactory) {
        this.position = position;
        this.startingColor = startingColor;
        this.endingColor = endingColor;
        this.particleBuilderFactory = particleBuilderFactory;
    }

    public ParticlePacket(PacketByteBuf buf) {
        this.position = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
        this.startingColor = buf.readInt();
        this.endingColor = buf.readInt();
        this.particleBuilderFactory = new DefaultParticleBuilderFactory(); // Use default factory if none provided
    }

    public void toBytes(PacketByteBuf buf) {
        buf.writeDouble(position.x);
        buf.writeDouble(position.y);
        buf.writeDouble(position.z);
        buf.writeInt(startingColor);
        buf.writeInt(endingColor);
    }

    public void handle(MinecraftClient client) {
        client.execute(() -> {
            World level = client.world;
            if (level != null) {
                Color startColor = new Color(startingColor);
                Color endColor = new Color(endingColor);
                particleBuilderFactory.build(level, position, startColor, endColor);
            }
        });
    }
}
