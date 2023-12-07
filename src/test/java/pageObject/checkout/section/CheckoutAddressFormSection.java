package pageObject.checkout.section;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.base.BasePage;

import java.time.Duration;

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

    public CheckoutAddressFormSection(WebDriver driver, Builder builder) {
        super(driver);
        setCompany(builder.company);
        setVatNumber(builder.vatNumber);
        setAddress(builder.address);
        setAddressComplement(builder.addressComplement);
        setPostalCode(builder.postalCode);
        setCity(builder.city);
        setPhone(builder.phone);
    }

    public void setCompany(String company) {
        sendKeys(companyInput,company);
    }

    public void setVatNumber(String vatNumber) {
        sendKeys(vatNumberInput, vatNumber);
    }

    public void setAddress(String address) {
        sendKeys(addressInput, address);
    }

    public void setAddressComplement(String addressComplement) {
        sendKeys(addressComplementInput, addressComplement);
    }

    public void setPostalCode(String postalCode) {
        sendKeys(postalCodeInput, postalCode);
    }

    public void setCity(String city) {
        sendKeys(cityInput, city);
    }

    public void selectCountry(String country) {
        click(countrySelect);
        new Select(countrySelect).selectByVisibleText(country);
        wait.until(ExpectedConditions.elementToBeClickable(countrySelect));
    }

    public void setPhone(String phone) {
        sendKeys(phoneInput, phone);
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

    public static class Builder {
        private String company;
        private String vatNumber;
        private String address;
        private String addressComplement;
        private String postalCode;
        private String city;
        private String phone;

        public Builder setCompany(String company) {
            this.company = company;
            return this;
        }

        public Builder setVatNumber(String vatNumber) {
            this.vatNumber = vatNumber;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setAddressComplement(String addressComplement) {
            this.addressComplement = addressComplement;
            return this;
        }

        public Builder setPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public CheckoutAddressFormSection build(WebDriver driver) {
            CheckoutAddressFormSection section = new CheckoutAddressFormSection(driver, this);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(section.phoneInput));
            return section;
        }
    }
}
