package tests.search;

import base.UrlProvider;
import driver.DriverSetUp;
import org.junit.jupiter.api.Test;
import pageObject.home.HomePage;
import pageObject.home.sections.SearchResultsSection;
import pageObject.home.sections.SearchWidgetSection;
import step.search.SearchPageStep;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SearchTest extends DriverSetUp {

    @Test
    public void shouldSearchDropDown() {
        driver.get(UrlProvider.HOME_URL.getUrl());

        at(SearchPageStep.class).performSearch(testContext);
        assertThat(at(SearchPageStep.class).isSearchQueryInAllDropdownItems(testContext))
                .as("Check if all dropdown items contain the search query")
                .isTrue();
    }

    @Test
    public void shouldFindProductInSearchResults() {
        driver.get(UrlProvider.HOME_URL.getUrl());


        String randomProductName = at(HomePage.class).getProductListSection().getRandomProductName();

        SearchWidgetSection searchWidget = at(HomePage.class).getSearchWidgetSection();
        searchWidget.performSearch(randomProductName);

        assertThat(at(SearchResultsSection.class).isProductNameInSearchResults(randomProductName))
                .as("Product with name " + randomProductName + " should be present in search results.")
                .isTrue();
    }
}
