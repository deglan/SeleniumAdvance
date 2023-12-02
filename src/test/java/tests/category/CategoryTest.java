package tests.category;

import base.UrlProvider;
import driver.DriverSetUp;
import org.junit.jupiter.api.Test;
import pageObject.category.FilterSection;
import pageObject.category.ProductCategoryPage;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoryTest extends DriverSetUp {

    @Test
    public void shouldCheckCategories() {
        driver.get(UrlProvider.HOME_URL.getUrl());
        ProductCategoryPage categoryPage = new ProductCategoryPage(driver);

        List<String> topCategories = categoryPage.getCategoryNames();
        for (String categoryName : topCategories) {
            categoryPage.selectCategory(categoryName);
            assertEquals(categoryName, categoryPage.getCurrentCategoryName().toUpperCase(), "Category name should match");
            assertTrue(categoryPage.isFiltersSidebarDisplayed(), "Filters side menu should be displayed");

            int expectedProductCount = categoryPage.getExpectedProductCount();
            int actualProductCount = categoryPage.getProductCount();
            assertEquals(expectedProductCount, actualProductCount, "Product count should match");
        }
    }

    @Test
    public void shouldCheckPriceFilter() {
        driver.get(UrlProvider.HOME_URL.getUrl());
        ProductCategoryPage categoryPage = new ProductCategoryPage(driver);
        FilterSection filterSection = new FilterSection(driver);
        categoryPage.selectCategory("Accessories");
        int initialProductCount = categoryPage.getProductCount();
        filterSection.setPrice(new BigDecimal("13.00"), new BigDecimal("15.00"));
        int filteredProductCount = categoryPage.getProductCount();

        assertTrue(filteredProductCount > 0, "Filtered products should be displayed");
        assertTrue(filteredProductCount <= initialProductCount, "Filtered product count should be less than or equal to the initial count");

        filterSection.clearFilter();
        int resetProductCount = categoryPage.getProductCount();

        assertEquals(initialProductCount, resetProductCount, "Product count should match after clearing filters");
    }
}
