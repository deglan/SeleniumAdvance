package pageObject.checkout.section;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObject.base.BasePage;

import java.util.List;

public class CompleteOrderSection extends BasePage {

    @FindBy(css = "div#order-details ul li")
    private List<WebElement> orderDetails;

    @FindBy(css = "table tr.total-value td:last-child")
    private WebElement totalAmount;

    public CompleteOrderSection(WebDriver driver) {
        super(driver);
    }

    public String getOrderReference() {
        return orderDetails.stream()
                .filter(e -> e.getText().startsWith("Order reference"))
                .findFirst()
                .map(e -> e.getText().split(":\\s+"))
                .map(parts -> parts.length > 1 ? parts[1] : "Not found")
                .orElse("Not found");
    }

    public String getPaymentMethod() {
        return orderDetails.stream()
                .filter(e -> e.getText().startsWith("Payment method"))
                .findFirst()
                .map(WebElement::getText)
                .orElse("Not found");
    }

    public String getShippingMethod() {
        return orderDetails.stream()
                .filter(e -> e.getText().startsWith("Shipping method"))
                .findFirst()
                .map(WebElement::getText)
                .orElse("Not found");
    }

    public String getTotalAmount() {
        return totalAmount.getText();
    }
}
