package pageObject.basket;

import model.Basket;
import model.BasketLine;
import model.converter.BasketModelConverter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObject.base.BasePage;

import java.util.List;

public class BasketPage extends BasePage implements BasketModelConverter {

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


    public BasketPage(WebDriver driver) {
        super(driver);
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
        click(checkoutButton);
    }


    public boolean isEmptyBasketLabelDisplayed() {
        try {
            return emptyBasketLabel.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public Basket toBasketModel() {
        Basket basket = new Basket();
        for (WebElement item : getCartItems()) {
            BasketLineSection lineSection = new BasketLineSection(driver, item);
            BasketLine basketLine = lineSection.toBasketLine();
            basket.addProduct(basketLine.getProduct(), basketLine.getQuantity());
        }
        return basket;
    }

    @Override
    public BasketLine toBasketLine() {
        return null;
    }

    public List<WebElement> getCartItems() {
        return cartItems;
    }
}
