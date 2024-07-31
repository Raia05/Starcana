package net.raia.starcana.item.enchantments.Prong;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class SmashingEnchantment extends Enchantment {
    public SmashingEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slotTypes) {
        super(weight, target, slotTypes);
    }

    @Override
    public int getMinPower(int level) {
        return level * 4;
    }

    @Override
    public int getMaxPower(int level) {
        return getMinPower(level) + 10;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}
