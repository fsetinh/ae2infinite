package com.venture.ae2infinite;

import com.venture.ae2infinite.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AE2Infinite implements ModInitializer {
	public static final String MOD_ID = "ae2infinite";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing AE2Infinite");
		ModItems.initialize();
	}
}