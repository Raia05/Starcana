package net.raia.starcana.block;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.raia.starcana.Starcana;
import net.raia.starcana.block.functional.StarGlass;
import net.raia.starcana.block.functional.StarLantern;

public class StarcanaBlocks {


    public static final Block STAR_GLASS = registerBlock("star_glass", new StarGlass(FabricBlockSettings
            .copyOf(Blocks.GLASS)
    ));
    public static final Block STARLANTERN = registerBlock("starcane_lamp", new StarLantern(FabricBlockSettings
            .copyOf(Blocks.LANTERN)
    ));


    private  static Block registerBlock(String name, Block block){
        registerBlocksItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Starcana.MOD_ID, name), block);
    }

    private static Item registerBlocksItem(String name, Block block){
        return Registry.register(Registries.ITEM, new Identifier(Starcana.MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        Starcana.LOGGER.info("registering Mod Blocks for "+ Starcana.MOD_ID);
    }
}
