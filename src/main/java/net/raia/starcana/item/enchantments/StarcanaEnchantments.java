package net.raia.starcana.item.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.raia.starcana.Starcana;
import net.raia.starcana.item.enchantments.Prong.SmashingEnchantment;

public class StarcanaEnchantments {

    public static Enchantment SMASHING = register("smashing",
            new SmashingEnchantment(Enchantment.Rarity.UNCOMMON, null, new EquipmentSlot[] {EquipmentSlot.MAINHAND}));

    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registries.ENCHANTMENT, new Identifier(Starcana.MOD_ID, name), enchantment);
    }

    public static void registerModEnchantments() {
        System.out.println("Registering Enchantments for " + Starcana.MOD_ID);
    }
}
