package pageObject.home.sections;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObject.base.BasePage;

import java.util.List;

public class SearchWidgetSection extends BasePage {

    @FindBy(id = "search_widget")
    private WebElement searchWidget;
    @FindBy(name = "s")
    private WebElement searchInput;
    @FindBy(css = "#search_widget > form > button")
    private WebElement searchButton;
    @Getter
    @FindBy(css = "#ui-id-1 .ui-menu-item")
    private List<WebElement> searchResultsDropdownItems;
    @FindBy(css = "selector-for-product-names")
    private List<WebElement> productNames;

    public SearchWidgetSection(WebDriver driver) {
        super(driver);
    }

    public void enterSearchQuery(String query) {
        sendKeys(searchInput, query);
    }

    public void performSearch(String query) {
        sendKeys(searchInput, query);
        click(searchButton);
    }

    public String getSearchInputValue() {
        return searchInput.getAttribute("value");
    }

    public boolean isProductNameInSearchResults(String productName) {
        return searchResultsDropdownItems.stream()
                .anyMatch(product -> product.getText().contains(productName));
    }
}
