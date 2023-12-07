package pageObject.product;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.base.BasePage;

import java.math.BigDecimal;
import java.time.Duration;

public class CartModal extends BasePage {

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

    public CartModal(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return productName.getText();
    }

    public BigDecimal getProductPrice() {
        return getBigDecimalFromElement(productPrice);
    }

    public BigDecimal getSubtotal() {
        wait.until(ExpectedConditions.visibilityOf(subtotal));
        return getBigDecimalFromElement(subtotal);
    }

    public BigDecimal getTotal() {
        return getBigDecimalFromElement(total);
    }

    public void continueShopping() {
        click(continueShoppingButton);
    }

    public void proceedToCheckout() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("blockcart-modal")));
        click(proceedToCheckoutButton);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("blockcart-modal")));
    }
}
