package pageObject.basket;

import com.fasterxml.jackson.databind.ser.Serializers;
import model.Basket;
import model.BasketLine;
import model.Product;
import model.converter.BasketModelConverter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import pageObject.base.BasePage;

import java.math.BigDecimal;

public class BasketLineSection extends BasePage implements BasketModelConverter {

    @FindBy(css = ".product-line-info a.label")
    private WebElement productName;

    @FindBy(css = ".product-line-info .price")
    private WebElement productPrice;

    @FindBy(css = ".js-cart-line-product-quantity")
    private WebElement productQuantity;

    @FindBy(css = ".remove-from-cart")
    private WebElement removeButton;

    public BasketLineSection(WebDriver driver, WebElement element) {
        super(driver, element);
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

    @Override
    public BasketLine toBasketLine() {
        String name = getProductName();
        BigDecimal price = new BigDecimal(getProductPrice().replace("$", ""));
        int quantity = getProductQuantity();
        Product product = new Product(name, price, quantity);
        return new BasketLine(product, price.multiply(BigDecimal.valueOf(quantity)), quantity);
    }

    @Override
    public Basket toBasketModel() {
        return null;
    }
}
