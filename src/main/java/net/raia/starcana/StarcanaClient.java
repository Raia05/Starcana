package net.raia.starcana;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.raia.starcana.entity.StarcanaEntities;
import net.raia.starcana.entity.client.FallenStarProjectileRenderer;
import net.raia.starcana.entity.client.FallenStarRenderer;


public class StarcanaClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(StarcanaEntities.FALLEN_STAR, FallenStarRenderer::new);
        EntityRendererRegistry.register(StarcanaEntities.FALLEN_STAR_PROJ, FallenStarProjectileRenderer::new);




    }
}
