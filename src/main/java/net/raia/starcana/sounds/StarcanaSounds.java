package net.raia.starcana.sounds;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.raia.starcana.Starcana;

public class StarcanaSounds {

    public static final SoundEvent PRONG_HIT_GROUND = registerSoundEvent("prong_hit_ground");


    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(Starcana.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
    public static void registerSounds()
    {
        Starcana.LOGGER.info("Registering Sounds for Starcana");
    }
}
