package com.rosgro.driver;

import com.rosgro.util.PropertyLoader;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverManager {

  private static final Logger logger = LogManager.getLogger();
  private static final PropertyLoader propertyLoader = PropertyLoader.getPropertyLoader();
  private static WebDriver webDriver;

  private DriverManager() {
  }

  public static WebDriver getDriver() throws MalformedURLException {
    if (webDriver == null) {
      switch (propertyLoader.getProperty("browser")) {
        case "firefox": {
          webDriver = new FirefoxDriver();
          WebDriverManager.firefoxdriver().setup();
          break;
        }
        default: {
          String username = "rosgro.uladzislau";
          String accessKey = "Mv787XyhezSYGFXIKMNVWGG6TYePrR2Q3uhxoxr5qUFKY7QCZa";
          DesiredCapabilities capabilities = new DesiredCapabilities();
          capabilities.setCapability("browserName", "Chrome");
          capabilities.setCapability("version", "92.0");
          capabilities.setCapability("platform", "Windows 10");
          capabilities.setCapability("resolution","1920x1080");
          capabilities.setCapability("build", "First Test");
          capabilities.setCapability("name", "Sample Test");
          capabilities.setCapability("network", true); // To enable network logs
          capabilities.setCapability("visual", true); // To enable step by step screenshot
          capabilities.setCapability("video", true); // To enable video recording
          capabilities.setCapability("console", true); // To capture console logs
          webDriver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub"), capabilities);
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
