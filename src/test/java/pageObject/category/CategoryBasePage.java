package pageObject.category;

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

public class CategoryBasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    @FindBy(css = "#js-product-list .product-miniature")
    protected List<WebElement> productWebElements;

    @FindBy(css = ".total-products p")
    protected WebElement totalProductsInfo;

    @FindBy(css = ".filter-item-label")
    protected List<WebElement> filterLabels;


    @FindBy(css = ".reset-filters")
    protected WebElement resetFiltersButton;

    public CategoryBasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            actions.scrollToElement(element);
            actions.scrollByAmount(0,10);
            element.click();
        }
    }

    public List<ProductElementMiniature> getProducts() {
        return productWebElements.stream()
                .map(ProductElementMiniature::new)
                .collect(Collectors.toList());
    }

    public int getProductCount() {
        return getProducts().size();
    }

}
