package tests.category;

import driver.DriverSetUp;
import org.junit.jupiter.api.Test;
import pageObject.category.FilterSection;
import pageObject.category.ProductCategoryPage;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryTest extends DriverSetUp {

    @Test
    public void shouldCheckCategories() {

        List<String> topCategories = at(ProductCategoryPage.class).getCategoryNames();
        for (String categoryName : topCategories) {
            at(ProductCategoryPage.class).selectCategory(categoryName);
            assertEquals(categoryName, at(ProductCategoryPage.class).getCurrentCategoryName().toUpperCase(), "Category name should match");
            assertThat(at(ProductCategoryPage.class).isFiltersSidebarDisplayed())
                    .as("Filters side menu should be displayed")
                    .isTrue();

            int expectedProductCount = at(ProductCategoryPage.class).getExpectedProductCount();
            int actualProductCount = at(ProductCategoryPage.class).getProductCount();
            assertEquals(expectedProductCount, actualProductCount, "Product count should match");
        }
    }

    @Test
    public void shouldCheckPriceFilter() {

        at(ProductCategoryPage.class)
                .selectCategory("Accessories");
        int initialProductCount = at(ProductCategoryPage.class).getProductCount();
        at(FilterSection.class)
                .setPriceRange(new BigDecimal("13.00"), new BigDecimal("15.00"));
        int filteredProductCount = at(ProductCategoryPage.class).getProductCount();

        assertThat(filteredProductCount > 0)
                .as("Filtered products should be displayed")
                .isTrue();
        assertThat(filteredProductCount <= initialProductCount)
                .as("Filtered product count should be less than or equal to the initial count")
                .isTrue();
        at(FilterSection.class).clearFilter();
        int resetProductCount = at(ProductCategoryPage.class).getProductCount();
        assertEquals(initialProductCount, resetProductCount, "Product count should match after clearing filters");
    }
}
