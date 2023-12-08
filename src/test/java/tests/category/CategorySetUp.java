package tests.category;

import base.UrlProvider;
import driver.DriverSetUp;
import org.junit.jupiter.api.BeforeEach;
import pageObject.category.FilterSection;
import pageObject.category.ProductCategoryPage;

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
