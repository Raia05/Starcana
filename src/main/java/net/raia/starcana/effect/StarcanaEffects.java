package net.raia.starcana.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.raia.starcana.Starcana;

public class StarcanaEffects {

    public static final StatusEffect STAR_SEARCHER = registerEffects("star_searcher", new StarSearcher());


    private  static StatusEffect registerEffects(String name, StatusEffect status){
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(Starcana.MOD_ID, name), status);
    }

    public static void registerModEffects() {
        Starcana.LOGGER.info("registering Mod effects for "+ Starcana.MOD_ID);
    }

}
