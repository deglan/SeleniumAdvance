package pageObject.checkout.section;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pageObject.base.BasePage;

@Slf4j
public class CheckoutAddressFormSection extends BasePage {

    @FindBy(name = "alias")
    private WebElement aliasInput;

    @FindBy(name = "firstname")
    private WebElement firstNameInput;

    @FindBy(name = "lastname")
    private WebElement lastNameInput;

    @FindBy(name = "company")
    private WebElement companyInput;

    @FindBy(name = "vat_number")
    private WebElement vatNumberInput;

    @FindBy(name = "address1")
    private WebElement addressInput;

    @FindBy(name = "address2")
    private WebElement addressComplementInput;

    @FindBy(name = "postcode")
    private WebElement postalCodeInput;

    @FindBy(name = "city")
    private WebElement cityInput;

    @FindBy(name = "id_country")
    private WebElement countrySelect;

    @FindBy(name = "phone")
    private WebElement phoneInput;
    @FindBy(css = "button[value='1']")
    private WebElement continueButton;
    @FindBy(css = ".js-cancel-address.cancel-address")
    private WebElement cancelButton;

    @FindBy(id = "use_same_address")
    private WebElement useSameAddressCheckbox;

    public CheckoutAddressFormSection(WebDriver driver) {
        super(driver);
    }

    public CheckoutAddressFormSection setCompany(String company) {
        sendKeys(companyInput, company);
        return this;
    }

    public CheckoutAddressFormSection setVatNumber(String vatNumber) {
        sendKeys(vatNumberInput, vatNumber);
        return this;
    }

    public CheckoutAddressFormSection setAddress(String address) {
        sendKeys(addressInput, address);
        return this;
    }

    public CheckoutAddressFormSection setAddressComplement(String addressComplement) {
        sendKeys(addressComplementInput, addressComplement);
        return this;
    }

    public CheckoutAddressFormSection setPostalCode(String postalCode) {
        sendKeys(postalCodeInput, postalCode);
        return this;
    }

    public CheckoutAddressFormSection setCity(String city) {
        sendKeys(cityInput, city);
        return this;
    }

    public void selectCountry(String country) {
        selectByVisibleText(countrySelect, country);
    }

    public CheckoutAddressFormSection setPhone(String phone) {
        sendKeys(phoneInput, phone);
        return this;
    }

    public void checkUseSameAddress(boolean useSame) {
        if (useSameAddressCheckbox.isSelected() != useSame) {
            click(useSameAddressCheckbox);
        }
    }

    public void clickCancel() {
        click(cancelButton);
    }

    public void clickContinueButton(WebDriver driver) {
        click(continueButton);
    }
}

