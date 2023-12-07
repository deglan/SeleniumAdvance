package tests.search;

import base.UrlProvider;
import driver.DriverSetUp;
import utils.search.SearchPageHandler;
import org.junit.jupiter.api.Test;
import pageObject.home.HomePage;
import pageObject.home.sections.SearchResultsSection;
import pageObject.home.sections.SearchWidgetSection;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest extends SearchSetUp {

    @Test
    public void shouldSearchDropDown() {
        driver.get(UrlProvider.HOME_URL.getUrl());

        searchPageHandler.performSearch(testContext);
        assertThat(searchPageHandler.isSearchQueryInAllDropdownItems(testContext))
                .as("Check if all dropdown items contain the search query")
                .isTrue();
    }

    @Test
    public void shouldFindProductInSearchResults() {
        driver.get(UrlProvider.HOME_URL.getUrl());


        String randomProductName = homePage.getProductListSection().getRandomProductName();

        SearchWidgetSection searchWidget = homePage.getSearchWidgetSection();
        searchWidget.performSearch(randomProductName);

        assertTrue(searchResultsSection.isProductNameInSearchResults(randomProductName),
                "Product with name " + randomProductName + " should be present in search results.");
    }
}
