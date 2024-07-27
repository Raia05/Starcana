package net.raia.starcana.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.raia.starcana.Starcana;
import net.raia.starcana.emitters.Particle_debugging;
import net.raia.starcana.item.itemTypes.StarShardItem;

public class Starcanaitems {

    public static final Item STAR_SHARD = registerItem("star_shard", new StarShardItem(new FabricItemSettings()));
    public static final Item DEBUG_WAND = registerItem("debug_wand", new Particle_debugging(new FabricItemSettings()));


    private  static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(Starcana.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Starcana.LOGGER.info("registering Mod items for "+ Starcana.MOD_ID);
    }

}
