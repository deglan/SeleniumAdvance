package tests.basket;

import base.UrlProvider;
import driver.DriverSetUp;
import model.Basket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pageObject.basket.BasketPage;
import pageObject.home.HomePage;
import utils.search.SearchPageHandler;

public class BasketSetUp extends DriverSetUp {

    HomePage homePage;
    Basket testBasket;
    BasketPage basketPage;
    SearchPageHandler searchPageHandler;

    @BeforeEach
    public void setUpBasket() {
        driver.get(UrlProvider.HOME_URL.getUrl());
        homePage = new HomePage(driver);
        testBasket = new Basket();
        basketPage = new BasketPage(driver);
        searchPageHandler = new SearchPageHandler(driver);
    }
}
