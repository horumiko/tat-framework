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

import com.rosgro.page.MainPage;
import com.rosgro.model.Product;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
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


}
