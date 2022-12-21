package com.rosgro.page;

import static com.rosgro.util.constants.PageNaming.SEARCH_PAGE;

import com.rosgro.model.Product;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends AbstractPage {

  public static final String PRODUCT_TILE = "product-tile";
  public static final String DECLINE_COOKIE = "tracking-consent-decline";
  public static final String THUMB_LINK = "thumb-link";
  public static final String TITLE = "product-tile";
  public static final String HREF = "href";
  public static final String PRODUCT_PRICING = "product-pricing";
  public static final String PRODUCT_PRICE = "data-product-price";
  public static final String COUNTRY_SELECT = "country-selector-wrapper";
  public static final String COUNTRY = "countries";
  public static final String A = "a";


  public MainPage(WebDriver driver, String page) {
    super(driver, page);
  }

  public MainPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(className = PRODUCT_TILE)
  List<WebElement> productList;

  @FindBy(className = DECLINE_COOKIE)
  WebElement cookieDecline;

  public List<Product> createProductList() {
    List<Product> createdProductList = new ArrayList<>();
    for (var product : productList) {
      String name = product.findElement(By.className(THUMB_LINK)).getAttribute(TITLE);
      String url = product.findElement(By.className(THUMB_LINK)).getAttribute(HREF);
      double price = Double.parseDouble(
          product.findElement(By.className(PRODUCT_PRICING)).getAttribute(PRODUCT_PRICE));
      createdProductList.add(new Product(url, name, price));
    }
    return createdProductList;
  }


  public MainPage declineCookies() {
    wait.until(ExpectedConditions.presenceOfElementLocated(By.className(DECLINE_COOKIE)));
    cookieDecline.click();
    return this;
  }

  public MainPage search(String text) {
    driver.get(SEARCH_PAGE + text);
    return this;
  }

  public String changeCountry(){
    driver.findElement(By.className(COUNTRY_SELECT)).click();
    driver.get(driver.findElement(By.className(COUNTRY))
        .findElement(By.tagName(A))
        .getAttribute(HREF));
    String currentUrl = driver.getCurrentUrl();
    return currentUrl;
  }


}
