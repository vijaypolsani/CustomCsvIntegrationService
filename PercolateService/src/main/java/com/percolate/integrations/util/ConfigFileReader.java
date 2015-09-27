package com.percolate.integrations.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by vijay.polsani on 9/25/15.
 */
public class ConfigFileReader {

	private Properties configProp = new Properties();

	public Properties loadProperties() {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("config/config.properties");
		try {
			configProp.load(in);
		} catch (IOException e) {
			//throw new PercolateServiceConfigLoadingException(e.getLocalizedMessage(), "3000");
			e.printStackTrace();
		}
		return configProp;
	}
}
