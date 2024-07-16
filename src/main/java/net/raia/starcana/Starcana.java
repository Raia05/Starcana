package net.raia.starcana;

import net.fabricmc.api.ModInitializer;

import net.raia.starcana.item.ModItemGroups;
import net.raia.starcana.item.Moditems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Starcana implements ModInitializer {

	public  static  final String MOD_ID = "starcana";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		Moditems.registerModItems();
		LOGGER.info("Hello Fabric world!");
	}
}