package pageObject.checkout.section;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShippingSection {

    private WebDriver driver;
    private Actions actions;
    private WebDriverWait wait;

    @FindBy(id = "delivery_option_1")
    private WebElement pickUpInStoreOption;

    @FindBy(id = "delivery_option_2")
    private WebElement myCarrierOption;

    @FindBy(css = "button[value='1'][name='confirmDeliveryOption']")
    private WebElement continueButton;

    public ShippingSection(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectPickUpInStore() {
        pickUpInStoreOption.click();
    }

    public void selectMyCarrier() {
        myCarrierOption.click();
    }

    public void clickContinue() {
        try {
            actions.moveToElement(continueButton).perform();
            wait.until(ExpectedConditions.elementToBeClickable(continueButton));

            if (continueButton.isDisplayed() && continueButton.isEnabled()) {
                continueButton.click();
            }
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", continueButton);
        }
    }
}
