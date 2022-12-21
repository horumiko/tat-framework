package com.rosgro.driver;

import com.rosgro.util.PropertyLoader;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {

  private static final Logger logger = LogManager.getLogger();
  private static final PropertyLoader propertyLoader = PropertyLoader.getPropertyLoader();
  private static WebDriver webDriver;

  private DriverManager() {
  }

  public static WebDriver getDriver() {
    if (webDriver == null) {
      switch (propertyLoader.getProperty("browser")) {
        case "firefox": {
          webDriver = new FirefoxDriver();
          WebDriverManager.firefoxdriver().setup();
          break;
        }
        default: {
          webDriver = new ChromeDriver();
          WebDriverManager.chromedriver().setup();
          webDriver.manage().window().maximize();
          break;
        }
      }
      logger.info("WebDriver has started");
    }

    return webDriver;
  }

  public static void closeDriver(WebDriver driver) {
//    driver.quit();
//    driver = null;
  }
}
