package net.raia.starcana.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.raia.starcana.Starcana;
import net.raia.starcana.abilities.StarShardItem;

public class Moditems {

    public static final Item STAR_SHARD = registerItem("star_shard", new StarShardItem(new FabricItemSettings()));

    private  static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(Starcana.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Starcana.LOGGER.info("registering Mod items for "+ Starcana.MOD_ID);
    }

}
