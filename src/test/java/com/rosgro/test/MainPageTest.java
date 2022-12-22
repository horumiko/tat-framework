package com.rosgro.test;


import static com.rosgro.util.constants.PageNaming.CART_PAGE;
import static com.rosgro.util.constants.PageNaming.MAIN_PAGE;
import static com.rosgro.util.constants.PageNaming.MAIN_PAGE_ASCENDING;
import static com.rosgro.util.constants.PageNaming.MAIN_PAGE_DESCENDING;
import static com.rosgro.util.constants.PageNaming.PRODUCT_PAGE;
import static com.rosgro.util.constants.PageNaming.SEARCH_PAGE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;

import com.rosgro.page.CartPage;
import com.rosgro.page.MainPage;
import com.rosgro.model.Product;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.rosgro.page.ProductPage;
import org.testng.annotations.Test;

public class MainPageTest extends AbstractTest {

  @Test
  public void searchTest() {
    String textToSearch = "Mb";
    List<String> searchResult = new MainPage(driver, SEARCH_PAGE).search(textToSearch)
        .createProductList()
        .stream()
        .map(x -> x.getName().toLowerCase())
        .collect(Collectors.toList());
    System.out.println(searchResult);
    assertThat(searchResult, everyItem(containsString(textToSearch.toLowerCase())));
  }

  @Test
  public void sortAscendingTest() {
    List<Product> sortResult = new MainPage(driver, MAIN_PAGE_ASCENDING).createProductList();
    assertThat(sortResult, equalTo(sortResult.stream()
        .sorted(Comparator.comparing(Product::getPrice))
        .collect(Collectors.toList())));
  }

  @Test
  public void sortDescendingTest() {
    List<Product> sortResult = new MainPage(driver, MAIN_PAGE_DESCENDING).createProductList();
    assertThat(sortResult, equalTo(sortResult.stream()
        .sorted(Comparator.comparing(Product::getPrice,Comparator.reverseOrder()))
        .collect(Collectors.toList())));
  }

  @Test
  public void countryChangeTest(){
    String UnitedKingdomFilaLink = "https://fila.co.uk/";
    String LinkAfterCountryChange = new MainPage(driver,MAIN_PAGE).changeCountry();
    assertThat(LinkAfterCountryChange,equalTo(UnitedKingdomFilaLink));
  }

  @Test
  public void addManyToCart() {
    final String SECOND_PRODUCT_PAGE = "https://www.fila.com/mens-grant-hill-2/1BM01789.html?dwvar_1BM01789_size=11&dwvar_1BM01789_color=132&fromList=Category%3A%20Sneakers%20%26%20Lifestyle&gridposition=20&cgid=men-sneakers";
    Product productPageSneaker = new ProductPage(driver, PRODUCT_PAGE).addToCart().createProduct();
    Product secondSneaker = new ProductPage(driver, SECOND_PRODUCT_PAGE).addToCart().createProduct();
    Product cartPageSneaker = new CartPage(driver, CART_PAGE).findProduct().get(0);
    assertThat(productPageSneaker.getPrice() + secondSneaker.getPrice(), equalTo(cartPageSneaker.getPrice()));
  }



}
