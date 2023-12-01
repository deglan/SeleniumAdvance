package pageObject.home.sections;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchWidgetSection {

    private WebDriver driver;

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
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterSearchQuery(String query) {
        searchInput.clear();
        searchInput.sendKeys(query);
    }

    public void performSearch(String query) {
        enterSearchQuery(query);
        searchButton.click();
    }

    public String getSearchInputValue() {
        return searchInput.getAttribute("value");
    }

    public boolean isProductNameInSearchResults(String productName) {
        for (WebElement product : searchResultsDropdownItems) {
            if (product.getText().contains(productName)) {
                return true;
            }
        }
        return false;
    }
}
