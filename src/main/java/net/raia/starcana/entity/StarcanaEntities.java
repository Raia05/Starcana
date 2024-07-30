package net.raia.starcana.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.raia.starcana.Starcana;
import net.raia.starcana.entity.mobs.FallenStarEntity;
import net.raia.starcana.entity.mobs.FallenStarProjectile;
import net.raia.starcana.entity.mobs.StarProngEntity;

public class StarcanaEntities
{
        public static final EntityType<FallenStarEntity> FALLEN_STAR = Registry.register(Registries.ENTITY_TYPE,
                new Identifier(Starcana.MOD_ID, "fallen_star"),
                FabricEntityTypeBuilder.create(SpawnGroup.MISC, FallenStarEntity::new)
                        .dimensions(EntityDimensions.fixed(1f,1f)).build());

        public static final EntityType<FallenStarProjectile> FALLEN_STAR_PROJ = Registry.register(Registries.ENTITY_TYPE,
                new Identifier(Starcana.MOD_ID, "shooting_star"),
                FabricEntityTypeBuilder.<FallenStarProjectile>create(SpawnGroup.MISC, FallenStarProjectile::new)
                        .dimensions(EntityDimensions.fixed(.25f,.25f)).build());

        public static final EntityType<StarProngEntity> STAR_PRONG = Registry.register(Registries.ENTITY_TYPE,
                new Identifier(Starcana.MOD_ID, "star_prong"),
                FabricEntityTypeBuilder.<StarProngEntity>create(SpawnGroup.MISC, StarProngEntity::new)
                        .dimensions(EntityDimensions.fixed(.25f,2f)).build());
}
