package tests.search;

import base.UrlProvider;
import configuration.TestContext;
import driver.DriverSetUp;
import formHandler.search.SearchPageHandler;
import org.junit.jupiter.api.Test;
import pageObject.home.HomePage;
import pageObject.home.sections.ProductListSection;
import pageObject.home.sections.SearchResultsSection;
import pageObject.home.sections.SearchWidgetSection;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest extends DriverSetUp {

    private SearchPageHandler searchPageHandler;

    @Test
    public void shouldSearchDropDown() {
        driver.get(UrlProvider.HOME_URL.getUrl());
        searchPageHandler = new SearchPageHandler(driver);

        searchPageHandler.performSearch(testContext);

        assertThat(searchPageHandler.isSearchQueryInAllDropdownItems(testContext))
                .as("Check if all dropdown items contain the search query")
                .isTrue();
    }

    @Test
    public void shouldFindProductInSearchResults() {
        driver.get(UrlProvider.HOME_URL.getUrl());
        HomePage homePage = new HomePage(driver);
        SearchResultsSection searchResultsSection = new SearchResultsSection(driver);

        String randomProductName = homePage.getProductListSection().getRandomProductName();

        SearchWidgetSection searchWidget = homePage.getSearchWidgetSection();
        searchWidget.performSearch(randomProductName);

        assertTrue(searchResultsSection.isProductNameInSearchResults(randomProductName),
                "Product with name " + randomProductName + " should be present in search results.");
    }
}
