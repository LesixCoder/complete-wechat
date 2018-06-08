package com.lunchtasting.server.util;

/**
 * 
 */


import java.io.*;
import java.util.Properties;

import org.apache.log4j.Logger;


/**
 * @author xuqian
 *
 */
public class ConfigManager {
	private Logger logger = Logger.getLogger(ConfigManager.class);
	
	public static String CONFIG_FILENAME = "";
	private static long TIME = 0;
	private static Properties CONFIG;
	private static ConfigManager INSTANCE;
	
	private ConfigManager() {
		loadConfig();
	}
	
	public static ConfigManager getInstance() {
		if (INSTANCE == null) {
			synchronized (ConfigManager.class) {
				INSTANCE = new ConfigManager();
			}
		}
		
		return INSTANCE;
	}
	
	private void loadConfig() {
		loadConfig(CONFIG_FILENAME);
	}
	
	private void loadConfig(String fileName) {
		CONFIG_FILENAME = fileName;
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
		CONFIG = new Properties();
		
		try {
			CONFIG.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
			return;
		}
		
		TIME = System.currentTimeMillis();
	}
	
	private void reloadConfig() {
		loadConfig();
	}
	
	public Properties getConfig() {
		long time = DateTimeUtils.nSecondAfterNow(-60);
		
		if(TIME < time) {
			reloadConfig();
		}
		
		return CONFIG;
	}
	
	public Properties getConfig(String fileName) {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
		CONFIG = new Properties();		
		try {
			CONFIG.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
			return null;
		}
		return CONFIG;
	}
	
	public static Properties getProperties(String name){
		//ConfigManager.CONFIG_FILENAME = name;
		return ConfigManager.getInstance().getConfig(name);
	}
	public static void main(String[] args) {
		Properties config = ConfigManager.getInstance().getConfig();
		System.out.println();
	}
}
