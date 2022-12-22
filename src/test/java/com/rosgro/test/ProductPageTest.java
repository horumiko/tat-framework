package com.rosgro.test;

import static com.rosgro.util.constants.PageNaming.CART_PAGE;
import static com.rosgro.util.constants.PageNaming.PRODUCT_PAGE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.rosgro.model.Product;
import com.rosgro.page.CartPage;
import com.rosgro.page.ProductPage;
import net.bytebuddy.build.Plugin;
import org.testng.annotations.Test;

public class ProductPageTest extends AbstractTest {

    @Test
    public void addToCart() {
        //Product productPageSneaker = new ProductPage(driver, PRODUCT_PAGE).addToCart().createProduct();
        //Product cartPageSneaker = new CartPage(driver, CART_PAGE).findProduct().get(0);
        //assertThat(productPageSneaker, equalTo(cartPageSneaker));
    }
    @Test
    public void removeFromCart() {
        //final String emptyBagText = "Your Bag (0)";
        //new ProductPage(driver, PRODUCT_PAGE).addToCart();
        //String currentBagText = new CartPage(driver, CART_PAGE).deleteFromCart().findCurrentCartText();
        //assertThat(currentBagText, equalTo(emptyBagText));
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
