package pageObject.checkout.section;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObject.base.BasePage;

public class PaymentSection extends BasePage {

    @FindBy(id = "payment-option-1")
    private WebElement payByCheckOption;

    @FindBy(id = "conditions_to_approve[terms-and-conditions]")
    private WebElement termsAndConditionsCheckbox;

    @FindBy(css = "#payment-confirmation button[type='submit']")
    private WebElement placeOrderButton;

    public PaymentSection(WebDriver driver) {
        super(driver);
    }

    public void selectPayByCheck() {
        payByCheckOption.click();
    }

    public void agreeToTermsAndConditions() {
        termsAndConditionsCheckbox.click();
    }

    public void clickPlaceOrder() {
        click(placeOrderButton);
    }
}
