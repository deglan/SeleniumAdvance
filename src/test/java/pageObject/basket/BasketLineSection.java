package pageObject.basket;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import pageObject.base.BasePage;

public class BasketLineSection {

    @FindBy(css = ".product-line-info a.label")
    private WebElement productName;

    @FindBy(css = ".product-line-info .price")
    private WebElement productPrice;

    @FindBy(css = ".js-cart-line-product-quantity")
    private WebElement productQuantity;

    @FindBy(css = ".remove-from-cart")
    private WebElement removeButton;

    public BasketLineSection(WebDriver driver, WebElement container) {
        PageFactory.initElements(new DefaultElementLocatorFactory(container), this);
    }

    public String getProductName() {
        return productName != null ? productName.getText() : "Unknown Product";
    }

    public String getProductPrice() {
        return productPrice.getText();
    }

    public int getProductQuantity() {
        return Integer.parseInt(productQuantity.getAttribute("value"));
    }

    public void removeProduct() {
        removeButton.click();
    }
}
