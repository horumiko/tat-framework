package com.rosgro.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertyLoader {

  private static final Properties properties = new Properties();
  private static final String DRIVER_PROPERTIES = "src/test/resources/driver.properties";
  private static PropertyLoader propertyLoader;
  private static Logger logger = LogManager.getLogger();

  private PropertyLoader() {};

  public static PropertyLoader getPropertyLoader() {
    try (FileInputStream fileInputStream = new FileInputStream(DRIVER_PROPERTIES)) {
      if (propertyLoader == null) {
        properties.load(fileInputStream);
        propertyLoader = new PropertyLoader();
        logger.info("Properties was successfully initialized");
      }
    } catch (IOException e) {
      logger.error("Error while initializing properties");
      throw new RuntimeException(e);
    }
    return propertyLoader;
  }

  public String getProperty(String text) {
    return properties.getProperty(text);
  }

}
