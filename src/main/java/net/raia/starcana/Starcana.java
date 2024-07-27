package net.raia.starcana;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.raia.starcana.item.StarcanaItemGroups;
import net.raia.starcana.item.Starcanaitems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Starcana implements ModInitializer {

	public  static  final String MOD_ID = "starcana";



    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final DefaultParticleType  STUFF = FabricParticleTypes.simple();

	@Override
	public void onInitialize() {
		StarcanaItemGroups.registerItemGroups();
		Starcanaitems.registerModItems();
		LOGGER.info("Hello Fabric world!");
	}
}