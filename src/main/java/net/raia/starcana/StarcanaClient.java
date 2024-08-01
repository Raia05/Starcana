package net.raia.starcana;

import dev.emi.trinkets.api.client.TrinketRenderer;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.raia.starcana.client.particle.StarcanaParticle;
import net.raia.starcana.entity.StarcanaEntities;
import net.raia.starcana.entity.client.*;
import net.raia.starcana.item.Starcanaitems;


public class StarcanaClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(StarcanaEntities.FALLEN_STAR, FallenStarRenderer::new);
        EntityRendererRegistry.register(StarcanaEntities.FALLEN_STAR_PROJ, FallenStarProjectileRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.STAR_PRONG, StarProngModel::getTexturedModelData);
        EntityRendererRegistry.register(StarcanaEntities.STAR_PRONG, StarProngRenderer::new);

        TrinketRendererRegistry.registerRenderer(Starcanaitems.STAR_PRONG, (TrinketRenderer) Starcanaitems.STAR_PRONG);
        StarcanaParticle.registerParticleFactory();




    }
}
