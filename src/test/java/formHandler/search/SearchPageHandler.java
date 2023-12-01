package formHandler.search;

import configuration.TestContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObject.home.sections.SearchWidgetSection;


import java.util.List;

public class SearchPageHandler {

    private SearchWidgetSection searchWidgetSection;


    public SearchPageHandler(WebDriver driver) {
        this.searchWidgetSection = new SearchWidgetSection(driver);
    }

    public void performSearch(TestContext testContext) {
        String searchQuery = testContext.getProperty("searchTest");
        searchWidgetSection.enterSearchQuery(searchQuery);
    }

    public boolean isSearchQueryInAllDropdownItems(TestContext testContext) {
        List<WebElement> items = searchWidgetSection.getSearchResultsDropdownItems();
        return items.stream().allMatch(item -> item.getText().contains(testContext.getProperty("searchTest")));
    }
}
