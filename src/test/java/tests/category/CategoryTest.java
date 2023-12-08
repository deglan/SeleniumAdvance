package tests.category;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoryTest extends CategorySetUp {

    @Test
    public void shouldCheckCategories() {

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
