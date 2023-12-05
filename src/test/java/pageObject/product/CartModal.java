package pageObject.product;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.time.Duration;

public class CartModal {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = ".product-name")
    private WebElement productName;

    @FindBy(css = ".product-price")
    private WebElement productPrice;

    @FindBy(css = ".subtotal.value")
    private WebElement subtotal;

    @FindBy(css = ".product-total > .value")
    private WebElement total;

    @FindBy(css = ".btn-secondary")
    private WebElement continueShoppingButton;

    @FindBy(css = "#blockcart-modal .modal-body .btn-primary")
    private WebElement proceedToCheckoutButton;

    // Constructor
    public CartModal(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Methods
    public String getProductName() {
        return productName.getText();
    }

    public BigDecimal getProductPrice() {
        String priceText = productPrice.getText().replace("$", "").trim();
        return new BigDecimal(priceText);
    }

    public BigDecimal getSubtotal() {
        wait.until(ExpectedConditions.visibilityOf(subtotal));
        String subtotalText = subtotal.getText().replace("$", "").trim();
        return new BigDecimal(subtotalText);
    }

    public BigDecimal getTotal() {
        String totalText = total.getText().replace("$", "").trim();
        return new BigDecimal(totalText);
    }

    public void continueShopping() {
        continueShoppingButton.click();
    }

    public void proceedToCheckout() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("blockcart-modal")));
        proceedToCheckoutButton.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("blockcart-modal")));
    }
}
