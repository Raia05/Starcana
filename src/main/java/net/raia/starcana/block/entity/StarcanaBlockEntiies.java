package net.raia.starcana.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.raia.starcana.Starcana;
import net.raia.starcana.block.StarcanaBlocks;

import java.util.LinkedHashMap;

public class StarcanaBlockEntiies {

    public static final BlockEntityType<StarLanternEntity> STAR_LANTURN_ENTITY_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Starcana.MOD_ID, "starcane_lamp"),
            FabricBlockEntityTypeBuilder.create(StarLanternEntity::new,
                    StarcanaBlocks.STARLANTERN).build());


    public static void registerBlcokEntities()
    {
        Starcana.LOGGER.info("registering Block Entites for "+ Starcana.MOD_ID);
    }
}
