package net.raia.starcana;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.raia.starcana.effect.StarSearcher;
import net.raia.starcana.item.StarcanaItemGroups;
import net.raia.starcana.item.Starcanaitems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Starcana implements ModInitializer {

	public  static  final String MOD_ID = "starcana";
	public static final StatusEffect STAR_SEARCHER = new StarSearcher();



	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final DefaultParticleType  STUFF = FabricParticleTypes.simple();

	@Override
	public void onInitialize() {
		StarcanaItemGroups.registerItemGroups();
		Starcanaitems.registerModItems();
		Registry.register(Registries.STATUS_EFFECT, new Identifier(Starcana.MOD_ID, "star_searcher"), STAR_SEARCHER);
		LOGGER.info("Hello Fabric world!");
	}
}