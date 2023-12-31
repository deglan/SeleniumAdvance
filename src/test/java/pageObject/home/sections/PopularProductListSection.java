package pageObject.home.sections;

import pageObject.base.BasePage;
import pageObject.product.ProductElementMiniature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PopularProductListSection extends BasePage {

    @FindBy(css = ".featured-products")
    private WebElement featuredProductsSection;
    @FindBy(css = ".product-miniature")
    private List<WebElement> productMiniatureElements;


    public PopularProductListSection(WebDriver driver) {
        super(driver);
    }

    public List<ProductElementMiniature> getProductElements() {
        return productMiniatureElements.stream()
                .map(element -> new ProductElementMiniature(driver, element))
                .collect(Collectors.toList());
    }

    public ProductElementMiniature getRandomProductElement() {
        List<ProductElementMiniature> products = getProductElements();
        if (products.isEmpty()) {
            throw new IllegalStateException("No products found");
        }

        Random random = new Random();
        int randomIndex = random.nextInt(products.size());
        return products.get(randomIndex);
    }

    public String getRandomProductName() {
        ProductElementMiniature randomProduct = getRandomProductElement();
        return randomProduct.getTitle();
    }

    public boolean isProductNameInSearchResults(String productName) {
        return getProductElements().stream()
                .anyMatch(product -> product.isTitleMatching(productName));
    }
}
