package com.rosgro.util;

import com.rosgro.driver.DriverManager;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TestListener implements ITestListener {

  private final Logger log = LogManager.getRootLogger();

  @Override
  public void onTestStart(ITestResult result) {
    log.info(result.getTestClass().getName() + " started");
  }

  @Override
  public void onTestFailure(ITestResult result) {
    log.warn("Test " + result.getTestClass().getName() + " failed");
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
    String methodName = result.getName();
    if (!result.isSuccess()) {
      File scrFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
      try {
        String reportDirectory = "./src/test/resources/";
        File destFile = new File(
            reportDirectory + "/failure_screenshots/" + methodName + "_" + formatter.format(
                calendar.getTime()) + ".png");
        FileUtils.copyFile(scrFile, destFile);
        Reporter.log(
            "<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath()
                + "' height='100' width='100'/> </a>");
      } catch (IOException e) {
        log.error(e);
      }
    }

  }
}