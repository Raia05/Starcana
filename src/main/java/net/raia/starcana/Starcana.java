package net.raia.starcana;

import net.fabricmc.api.ModInitializer;

import net.raia.starcana.client.network.packets.StarcanaClientPackets;
import net.raia.starcana.client.network.packets.StarcanaPackets;
import net.raia.starcana.client.particle.StarcanaParticle;
import net.raia.starcana.effect.StarcanaEffects;
import net.raia.starcana.item.StarcanaItemGroups;
import net.raia.starcana.item.Starcanaitems;
import net.raia.starcana.item.enchantments.StarcanaEnchantments;
import net.raia.starcana.sounds.StarcanaSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.raia.starcana.client.particle.StarcanaParticle.PARTICLES;

public class Starcana implements ModInitializer {

	public  static  final String MOD_ID = "starcana";



	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		StarcanaItemGroups.registerItemGroups();
		Starcanaitems.registerModItems();
		StarcanaEffects.registerModEffects();
		StarcanaSounds.registerSounds();
		StarcanaEnchantments.registerModEnchantments();
		PARTICLES.register();



		StarcanaPackets.registerPackets();
		StarcanaClientPackets.registerClientPackets();
		LOGGER.info("Hello Fabric world!");
	}
}