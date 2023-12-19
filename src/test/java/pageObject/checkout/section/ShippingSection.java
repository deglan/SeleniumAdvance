package pageObject.checkout.section;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObject.base.BasePage;


public class ShippingSection extends BasePage {

    @FindBy(id = "delivery_option_1")
    private WebElement pickUpInStoreOption;

    @FindBy(id = "delivery_option_2")
    private WebElement myCarrierOption;

    @FindBy(css = "button[value='1'][name='confirmDeliveryOption']")
    private WebElement continueButton;

    public ShippingSection(WebDriver driver) {
        super(driver);
    }

    public void selectPickUpInStore() {
        click(pickUpInStoreOption);
    }

    public void selectMyCarrier() {
        click(myCarrierOption);
    }

    public void clickContinue() {
        click(continueButton);
    }
}
