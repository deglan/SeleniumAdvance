package pageObject.product;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import pageObject.base.BasePage;

import java.math.BigDecimal;

public class ProductPage extends BasePage {

    @FindBy(css = "h1.h1")
    private WebElement productName;
    @FindBy(css = ".current-price span[itemprop='price']")
    private WebElement productPrice;
    @FindBy(css = "button.add-to-cart")
    private WebElement addToCartButton;
    @FindBy(id = "description")
    private WebElement productDescription;
    @FindBy(css = "a[href='#product-details']")
    private WebElement productDetailsTab;
    @FindBy(id = "product-details")
    private WebElement productDetails;
    @FindBy(css = "input[name='qty']")
    private WebElement quantityInput;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return productName.getText();
    }

    public BigDecimal getProductPrice() {
        String priceText = productPrice.getText().replace("$", "").trim();
        return new BigDecimal(priceText);
    }

    public void addToCart() {
        click(addToCartButton);
    }

    public void setQuantity(int quantity) {
        sendKeys(quantityInput, String.valueOf(quantity));
    }
}
