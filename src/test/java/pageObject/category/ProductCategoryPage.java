package pageObject.category;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObject.product.ProductElementMiniature;

import java.util.List;
import java.util.stream.Collectors;

public class ProductCategoryPage extends CategoryBasePage {

    @FindBy(css = "#top-menu > .category > a")
    private List<WebElement> categories;

    @FindBy(css = "#search_filters_wrapper")
    private WebElement filtersSidebar;

    @FindBy(css = "nav.breadcrumb ol li:nth-child(2) span[itemprop='name']")
    private WebElement currentCategoryNameElement;

    public ProductCategoryPage(WebDriver driver) {
        super(driver);
    }

    public void selectCategory(String categoryName) {
        WebElement categoryElement = categories.stream()
                .filter(e -> e.getText().trim().equalsIgnoreCase(categoryName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Category not found: " + categoryName));

        click(categoryElement);
    }

    public String getCurrentCategoryName() {
        wait.until(ExpectedConditions.visibilityOf(currentCategoryNameElement));
        return currentCategoryNameElement.getText().trim().toUpperCase();
    }

    public boolean isFiltersSidebarDisplayed() {
        return filtersSidebar.isDisplayed();
    }

    public int getProductCount() {
        return productWebElements.size();
    }

    public List<String> getCategoryNames() {
        categories.forEach(cat -> System.out.println("Category name: " + cat.getText()));

        return categories.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public int getExpectedProductCount() {
        wait.until(ExpectedConditions.visibilityOf(totalProductsInfo));
        String countText = totalProductsInfo.getText().trim();
        return Integer.parseInt(countText.split(" ")[2]);
    }

    public WebElement getProductElement(String productName) {
        for (WebElement product : productWebElements) {
            WebElement titleElement = product.findElement(By.cssSelector(".h3.product-title a"));
            if (titleElement.getText().trim().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        throw new NoSuchElementException("Product not found: " + productName);
    }

    public ProductElementMiniature getProductElementMiniature(String productName) {
        WebElement productElement = getProductElement(productName);
        return new ProductElementMiniature(driver, productElement);
    }
}