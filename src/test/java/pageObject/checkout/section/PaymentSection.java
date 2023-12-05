package pageObject.checkout.section;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentSection {

    private WebDriver driver;

    @FindBy(id = "payment-option-1")
    private WebElement payByCheckOption;

    @FindBy(css = "input[name='conditions_to_approve[terms-and-conditions]']")
    private WebElement termsAndConditionsCheckbox;

    @FindBy(css = "#payment-confirmation button[type='submit']")
    private WebElement placeOrderButton;

    public PaymentSection(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectPayByCheck() {
        payByCheckOption.click();
    }

    public void agreeToTermsAndConditions() {
        if (!termsAndConditionsCheckbox.isSelected()) {
            termsAndConditionsCheckbox.click();
        }
    }

    public void clickPlaceOrder() {
        placeOrderButton.click();
    }
}
