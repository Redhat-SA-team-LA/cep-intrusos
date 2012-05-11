package com.redhat.seginf.ui.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SystemProperties {

	private static SystemProperties instance;

	public static SystemProperties getInstance() {

		if (instance == null)
			instance = new SystemProperties();
		return instance;

	}

	/* ******************************
	 * OBJECT *****************************
	 */

	private Properties properties;

	public SystemProperties() {

		this.properties = new Properties();
		try {
			properties.load(new FileInputStream(
					"src/main/resources/config.properties"));
			System.out.println("Configuration Loaded");

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Configuration could not be loaded");
		}

	}

	public boolean getValue(String key) {

		return Boolean.getBoolean(this.properties.getProperty(key));

	}

}
