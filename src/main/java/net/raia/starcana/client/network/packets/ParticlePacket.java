package net.raia.starcana.client.network.packets;

import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.raia.starcana.client.particle.DefaultParticleBuilderFactory;
import net.raia.starcana.client.particle.ParticleBuilderFactory;
import net.raia.starcana.client.particle.ParticleBuilderFactoryRegistry;


import java.awt.*;
public class ParticlePacket {
    private final Vec3d position;
    private final int startingColor;
    private final int endingColor;
    private final String factoryId;

    public ParticlePacket(Vec3d position, int startingColor, int endingColor, String factoryId) {
        this.position = position;
        this.startingColor = startingColor;
        this.endingColor = endingColor;
        this.factoryId = factoryId;
    }

    public ParticlePacket(PacketByteBuf buf) {
        this.position = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
        this.startingColor = buf.readInt();
        this.endingColor = buf.readInt();
        this.factoryId = buf.readString(32767);
    }

    public void toBytes(PacketByteBuf buf) {
        buf.writeDouble(position.x);
        buf.writeDouble(position.y);
        buf.writeDouble(position.z);
        buf.writeInt(startingColor);
        buf.writeInt(endingColor);
        buf.writeString(factoryId);
    }

    public void handle(MinecraftClient client) {
        client.execute(() -> {
            World level = client.world;
            if (level != null) {
                Color startColor = new Color(startingColor);
                Color endColor = new Color(endingColor);
                ParticleBuilderFactory factory = ParticleBuilderFactoryRegistry.getFactory(factoryId);
                if (factory != null) {
                    factory.build(level, position, startColor, endColor);
                }
            }
        });
    }
}
