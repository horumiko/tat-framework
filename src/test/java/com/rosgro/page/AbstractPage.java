package com.rosgro.page;

import com.rosgro.util.PropertyLoader;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {

  protected WebDriver driver;
  protected WebDriverWait wait;

  protected PropertyLoader propertyLoader = PropertyLoader.getPropertyLoader();;

  protected AbstractPage(WebDriver driver, String page) {
    this.driver = driver;
    driver.get(page);
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    PageFactory.initElements(driver, this);
  }

  protected AbstractPage(WebDriver driver) {
    this.driver = driver;
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    PageFactory.initElements(driver, this);
  }

}
