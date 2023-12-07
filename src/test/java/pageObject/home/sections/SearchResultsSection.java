package pageObject.home.sections;

import pageObject.base.BasePage;
import pageObject.product.ProductElementMiniature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class SearchResultsSection extends BasePage {

    @FindBy(css = ".product-miniature")
    private List<WebElement> productMiniatures;

    public SearchResultsSection(WebDriver driver) {
        super(driver);
    }

    public List<ProductElementMiniature> getSearchResults() {
        return productMiniatures.stream().map(ProductElementMiniature::new).collect(Collectors.toList());
    }

    public boolean isProductNameInSearchResults(String productName) {
        return getSearchResults().stream().anyMatch(product -> product.getTitle().equalsIgnoreCase(productName));
    }
}
