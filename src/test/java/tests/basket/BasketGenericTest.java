package tests.basket;

import base.UrlProvider;
import driver.DriverSetUp;
import model.Basket;
import model.BasketLine;
import model.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pageObject.basket.BasketPage;
import pageObject.home.HomePage;
import pageObject.product.ProductElementMiniature;
import pageObject.product.ProductPage;
import utils.basket.BasketHandler;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Random;

public class BasketGenericTest extends DriverSetUp {

    @Test
    public void shouldCheckBasket() {
            driver.get(UrlProvider.HOME_URL.getUrl());

            HomePage homePage = new HomePage(driver);
            Basket testBasket = new Basket();

            for (int i = 0; i < 10; i++) {
                BasketHandler.addRandomProductToBasket(driver, homePage, testBasket);
            }

            driver.get(UrlProvider.BASKET_URL.getUrl());
            BasketPage basketPage = new BasketPage(driver);

            // Verification steps
            BasketHandler.verifyBasketContents(testBasket, basketPage);
            BasketHandler.verifyTotalPrice(testBasket, basketPage);

    }

    @Test
    public void shouldRemoveProductsFromBasket() {

        driver.get(UrlProvider.HOME_URL.getUrl());
        HomePage homePage = new HomePage(driver);
        Basket testBasket = new Basket();

        // Add two random products to the basket
        BasketHandler.addRandomProductToBasket(driver, homePage, testBasket);
        BasketHandler.addRandomProductToBasket(driver, homePage, testBasket);

        // Go to basket page
        driver.get(UrlProvider.BASKET_URL.getUrl());
        BasketPage basketPage = new BasketPage(driver);

        // Verify initial total price
        BasketHandler.verifyTotalPrice(testBasket, basketPage);

        // Remove the first product
        basketPage.removeProduct(testBasket.getProducts().keySet().iterator().next());
        testBasket.removeProduct(testBasket.getProducts().keySet().iterator().next());

        // Verify updated total price
        BasketHandler.verifyTotalPrice(testBasket, basketPage);

        // Remove the last product
        basketPage.removeProduct(testBasket.getProducts().keySet().iterator().next());
        testBasket.removeProduct(testBasket.getProducts().keySet().iterator().next());

        // Check if empty basket label is displayed
        Assertions.assertThat(basketPage.isEmptyBasketLabelDisplayed()).as("Check if empty basket label is displayed").isTrue();
    }

}
