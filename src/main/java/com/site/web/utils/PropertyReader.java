package com.site.web.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

	
	private PropertyReader() {
		// All methods are static, so restrict object creation
	}

	public static Properties readPropertiesFile(String fileName) throws IOException {
	      FileInputStream fis = null;
	      Properties prop = null;
	      try {
	         fis = new FileInputStream(fileName);
	         prop = new Properties();
	         prop.load(fis);
	      } catch(FileNotFoundException fnfe) {
	         fnfe.printStackTrace();
	      } catch(IOException ioe) {
	         ioe.printStackTrace();
	      } finally {
	         fis.close();
	      }
	      return prop;
	}

	/**
	 * This method is used to load properties file without section and uses
	 * Properties class. It returns value for propName if it is present in
	 * properties else returns defaultVal
	 *
	 * @param fileName
	 * @param propName
	 * @param defaultVal
	 * @return String property value
	 */
	public static String getPropValue(String fileName, String propName) {
		Properties properties = null;
		try {
			properties = readPropertiesFile(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String value = properties.getProperty(propName);
		return value;
	}
}
