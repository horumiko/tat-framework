package com.rosgro.page;

import com.rosgro.model.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends AbstractPage {

  public static final String ADD_CART_BUTTON = "add_to_cart_button_col";
  public static final String PRODUCT_INFO = "product-info";
  public static final String PRODUCT_NAME = "product-name";
  public static final String PRODUCT_PRICE = "product-price";
  public static final String PRICE_GTM = "data-gtm-price";
  public static final String ADD_TO_CART= "add-to-cart";
  public static final String CHECKOUT = "checkout";


  public ProductPage(WebDriver driver, String page) {
    super(driver, page);
  }

  public ProductPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(className = ADD_CART_BUTTON)
  WebElement addButton;

  @FindBy(className = PRODUCT_INFO)
  WebElement product;

  public Product createProduct() {
      String name = product.findElement(By.className(PRODUCT_NAME)).getText();
      String url = driver.getCurrentUrl();
      double price = Double.parseDouble(
          product.findElement(By.className(PRODUCT_PRICE)).getAttribute(PRICE_GTM));
    return new Product(url, name, price);
  }

  public ProductPage addToCart() {
    driver.findElement(By.id(ADD_TO_CART)).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.className(CHECKOUT)));
    return this;
  }

}
