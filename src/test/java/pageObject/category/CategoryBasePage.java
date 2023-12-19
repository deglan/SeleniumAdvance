package pageObject.category;

import pageObject.base.BasePage;
import pageObject.product.ProductElementMiniature;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryBasePage extends BasePage {

    @FindBy(css = "#js-product-list .product-miniature")
    protected List<WebElement> productWebElements;

    @FindBy(css = ".total-products p")
    protected WebElement totalProductsInfo;

    @FindBy(css = ".filter-item-label")
    protected List<WebElement> filterLabels;


    @FindBy(css = ".reset-filters")
    protected WebElement resetFiltersButton;

    public CategoryBasePage(WebDriver driver) {
        super(driver);
    }

    public List<ProductElementMiniature> getProducts() {
        return productWebElements.stream()
                .map(element -> new ProductElementMiniature(driver, element))
                .collect(Collectors.toList());
    }

    public int getProductCount() {
        return getProducts().size();
    }

}
