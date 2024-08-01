package net.raia.starcana.utils.helper;

import net.minecraft.item.ItemStack;
import net.raia.starcana.item.enchantments.StarcanaEnchantments;

import static net.minecraft.enchantment.EnchantmentHelper.getLevel;

public class StarcanaEnchantmentHelper {

    public static int getSmashing(ItemStack stack) {
        return getLevel(StarcanaEnchantments.SMASHING, stack);
    }
}
