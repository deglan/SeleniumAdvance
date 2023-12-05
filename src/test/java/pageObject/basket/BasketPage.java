package pageObject.basket;

import model.BasketLine;
import model.Product;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasketPage {

    @FindBy(id = "cart-subtotal-products")
    private WebElement subtotalProducts;

    @FindBy(css = "#cart-subtotal-shipping .value")
    private WebElement subtotalShipping;

    @FindBy(css = ".cart-total .value")
    private WebElement total;

    @FindBy(css = ".checkout .btn-primary")
    private WebElement checkoutButton;

    @FindBy(css = ".cart-items .cart-item")
    private List<WebElement> cartItems;

    @FindBy(css = ".no-items")
    private WebElement emptyBasketLabel;

    private Map<String, BasketLine> productsMap;
    private WebDriver driver;
    private WebDriverWait wait;

    public BasketPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        productsMap = new HashMap<>();
        initializeProductsMap();
    }

    private void initializeProductsMap() {
        for (WebElement item : cartItems) {
            BasketLineSection lineSection = new BasketLineSection(driver, item);
            String name = lineSection.getProductName();
            BigDecimal price = new BigDecimal(lineSection.getProductPrice().replace("$", ""));
            int quantity = lineSection.getProductQuantity();

            Product product = new Product(name, price, quantity);
            BasketLine basketLine = new BasketLine(product, price.multiply(BigDecimal.valueOf(quantity)), quantity);

            productsMap.put(name, basketLine);
        }
    }

    public String getSubtotalProducts() {
        return subtotalProducts.getText();
    }

    public String getSubtotalShipping() {
        return subtotalShipping.getText();
    }

    public String getTotal() {
        return total.getText();
    }

    public void proceedToCheckout() {
        checkoutButton.click();
    }

    public BasketLine getProductDetails(String productName) {
        return productsMap.get(productName);
    }

    public boolean isEmptyBasketLabelDisplayed() {
        try {
            return emptyBasketLabel.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void removeProduct(String productName) {
        for (WebElement item : cartItems) {
            BasketLineSection lineSection = new BasketLineSection(driver, item);
            if (lineSection.getProductName().equals(productName)) {
                lineSection.removeProduct();
                wait.until(ExpectedConditions.stalenessOf(item));
                break;
            }
        }
        productsMap.remove(productName);
    }
}
