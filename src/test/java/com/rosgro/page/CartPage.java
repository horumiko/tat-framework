package com.rosgro.page;

import com.rosgro.model.Product;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends AbstractPage {

  public static final String CART_ROW = "cart-row";
  public static final String REMOVE_BUTTON = "button-remove";
  public static final String TOGGLE_CART = "toggle-cart";
  public static final String ITEM_PRICE = "item-price";
  public static final String NAME = "name";
  public static final String A = "a";
  public static final String HREF = "href";

  public static final String ORDER_SUBTOTAL = "order-subtotal";


  public CartPage(WebDriver driver, String page) {
    super(driver, page);
  }

  public CartPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(className = CART_ROW)
  List<WebElement> productList;

  @FindBy(className = REMOVE_BUTTON)
  WebElement removeButton;

  @FindBy(className = TOGGLE_CART)
  WebElement cartText;

  public List<Product> findProduct() {
    wait.until(ExpectedConditions.presenceOfElementLocated(By.className(ITEM_PRICE)));
    List<Product> createdProductList = new ArrayList<>();
    for (var product : productList) {
      String name = product.findElement(By.className(NAME)).getText();
      String url = product.findElement(By.className(NAME))
          .findElement(By.tagName(A))
          .getAttribute(HREF);
      double price = Double.parseDouble(
          driver.findElement(By.className(ORDER_SUBTOTAL)).getText().replaceAll("[^\\d.]", ""));
      createdProductList.add(new Product(url, name, price));
    }
    return createdProductList;
  }

  public CartPage deleteFromCart() {
    ((JavascriptExecutor)driver).executeScript("arguments[0].click();",removeButton);
    return this;
  }

  public String findCurrentCartText(){
    return cartText.getAccessibleName();
  }
}
