package step.search;

import configuration.TestContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObject.base.BasePage;
import pageObject.home.sections.SearchWidgetSection;

import java.util.List;

public class SearchPageStep extends BasePage {

    private SearchWidgetSection searchWidgetSection;

    public SearchPageStep(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public SearchPageStep(WebDriver driver) {
        super(driver);
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
