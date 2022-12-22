package com.rosgro.test;

import static com.rosgro.util.constants.PageNaming.MAIN_PAGE;

import com.rosgro.driver.DriverManager;
import com.rosgro.page.MainPage;
import com.rosgro.util.PropertyLoader;
import com.rosgro.util.TestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.net.MalformedURLException;

@Listeners({TestListener.class})
public abstract class AbstractTest {

  protected WebDriver driver;
  protected PropertyLoader propertyLoader;

  @BeforeSuite
  public void setupSuite() throws MalformedURLException {
    this.driver = DriverManager.getDriver();
    this.propertyLoader = PropertyLoader.getPropertyLoader();
    declineCookies();
  }

  @BeforeClass
  public void setupBrowser() throws MalformedURLException {
    this.driver = DriverManager.getDriver();
    this.propertyLoader = PropertyLoader.getPropertyLoader();
  }

  @AfterSuite(alwaysRun = true)
  public void quitBrowser() {
    DriverManager.closeDriver(driver);
  }

  public void declineCookies(){
    new MainPage(driver,MAIN_PAGE).declineCookies();
  }



}
