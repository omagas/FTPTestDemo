package com.zuich.life.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesTool {
	public static Properties getProperties(String propertiesFile) throws FileNotFoundException, IOException{

		Properties properties = new Properties();

		// ?�properties?�jar裡�?properties�???��?
//	    properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFile));

		// ?�properties?�jar�??properties�???��?
		//properties.load(new FileInputStream(System.getProperty("user.dir") + "/properties/" + propertiesFile));
                properties.load(new FileInputStream(System.getProperty("user.dir") + "/properties/" + propertiesFile));
		return properties;
	}
}
