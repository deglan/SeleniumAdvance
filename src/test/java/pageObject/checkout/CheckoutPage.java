package pageObject.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObject.base.BasePage;

public class CheckoutPage extends BasePage {

    @FindBy(id = "checkout-addresses-step")
    private WebElement checkoutAddressesStepSection;

    @FindBy(id = "delivery-addresses")
    private WebElement deliveryAddresses;

    @FindBy(css = ".edit-address")
    private WebElement editAddressButton;

    @FindBy(css = ".delete-address")
    private WebElement deleteAddressButton;

    @FindBy(css = ".add-address a")
    private WebElement addNewAddressLink;

    @FindBy(css = "a[href='http://146.59.32.4/index.php?controller=order&newAddress=invoice']")
    private WebElement addNewInvoiceAddressLink;

    @FindBy(css = "a[data-link-action='different-invoice-address']")
    private WebElement differentInvoiceAddressLink;

    @FindBy(css = ".btn.btn-primary.continue")
    private WebElement continueButton;

    @FindBy(css = ".js-address-error")
    private WebElement addressErrorAlert;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCheckoutAddressesStepDisplayed() {
        return checkoutAddressesStepSection.isDisplayed();
    }

    public void selectDeliveryAddressById(String addressId) {
        WebElement addressRadioBtn = deliveryAddresses.findElement(By.id("id-address-delivery-address-" + addressId));
        addressRadioBtn.findElement(By.tagName("input")).click();
    }

    public void clickEditAddress() {
        click(editAddressButton);
    }

    public void clickDeleteAddress() {
        click(deleteAddressButton);
    }

    public void clickAddNewAddress() {
        click(addNewAddressLink);
    }

    public void clickAddNewInvoiceAddress() {
        click(addNewInvoiceAddressLink);
    }

    public void clickDifferentInvoiceAddress() {
        click(differentInvoiceAddressLink);
    }

    public void clickContinue() {
        click(continueButton);
    }

    public boolean isAddressErrorAlertDisplayed() {
        return addressErrorAlert.isDisplayed();
    }
}
