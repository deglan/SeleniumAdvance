package tests.category;

import base.UrlProvider;
import driver.DriverSetUp;
import model.Basket;
import org.junit.jupiter.api.BeforeEach;
import pageObject.basket.BasketPage;
import pageObject.category.FilterSection;
import pageObject.category.ProductCategoryPage;
import pageObject.home.HomePage;
import utils.search.SearchPageHandler;

public class CategorySetUp extends DriverSetUp {

    ProductCategoryPage categoryPage;
    FilterSection filterSection;

    @BeforeEach
    public void setUpCategory() {
        driver.get(UrlProvider.HOME_URL.getUrl());
        categoryPage = new ProductCategoryPage(driver);
        filterSection = new FilterSection(driver);
    }
}
