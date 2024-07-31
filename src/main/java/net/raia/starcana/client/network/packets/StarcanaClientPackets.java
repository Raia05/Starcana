package net.raia.starcana.client.network.packets;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
public class StarcanaClientPackets {
    public static void registerClientPackets() {
        ClientPlayNetworking.registerGlobalReceiver(StarcanaPackets.PARTICLE_SPAWN_ID, (client, handler, buf, responseSender) -> {
            ParticlePacket packet = new ParticlePacket(buf);
            packet.handle(MinecraftClient.getInstance());
        });
    }
}
