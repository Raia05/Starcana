package net.raia.starcana.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.raia.starcana.Starcana;

public class ModItemGroups {

    public static final ItemGroup STARCANA_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Starcana.MOD_ID, "starcana"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.starcana"))
                    .icon(() -> new ItemStack(Moditems.STAR_SHARD)).entries((displayContext, entries) -> {
                        entries.add(Moditems.STAR_SHARD);


                    }).build());
    public static void registerItemGroups() {
        Starcana.LOGGER.info("registering Starcana Tabs");
    }
}