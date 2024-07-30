package net.raia.starcana;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.entity.Entity;
import net.raia.starcana.entity.StarcanaEntities;
import net.raia.starcana.entity.client.*;


public class StarcanaClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(StarcanaEntities.FALLEN_STAR, FallenStarRenderer::new);
        EntityRendererRegistry.register(StarcanaEntities.FALLEN_STAR_PROJ, FallenStarProjectileRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.STAR_PRONG, StarProngModel::getTexturedModelData);
        EntityRendererRegistry.register(StarcanaEntities.STAR_PRONG, StarProngRenderer::new);





    }
}
