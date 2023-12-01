package pageObject.loginAndRegister;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@AllArgsConstructor
public class RegisterPage {

    private WebDriver driver;

    @FindBy(css = "input[name='id_gender'][value='1']")
    private WebElement genderMr;
    @FindBy(css = "input[name='id_gender'][value='2']")
    private WebElement genderMrs;
    @FindBy(name = "firstname")
    private WebElement firstNameLocator;
    @FindBy(name = "lastname")
    private WebElement lastNameLocator;
    @FindBy(name = "email")
    private WebElement emailLocator;
    @FindBy(name = "password")
    private WebElement passwordLocator;
    @FindBy(name = "birthday")
    private WebElement birthdateLocator;
    @FindBy(name = "optin")
    private WebElement offersOptInLocator;
    @FindBy(name = "customer_privacy")
    private WebElement customerPrivacyLocator;
    @FindBy(name = "newsletter")
    private WebElement newsletterOptInLocator;
    @FindBy(name = "psgdpr")
    private WebElement acceptTermsLocator;
    @FindBy(css = "button.btn.btn-primary.form-control-submit")
    private WebElement saveButtonLocator;
    @Getter
    @FindBy(css = ".alert.alert-danger")
    private WebElement errorMessage;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("Mr")) {
            genderMr.click();
        } else if (gender.equalsIgnoreCase("Mrs")) {
            genderMrs.click();
        }
    }

    public void enterFirstName(String firstName) {
        firstNameLocator.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameLocator.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        emailLocator.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordLocator.sendKeys(password);
    }

    public void enterBirthdate(String birthdate) {
        birthdateLocator.sendKeys(birthdate);
    }

    public void setOffersOptIn(boolean offersOptIn) {
        if (offersOptIn && !offersOptInLocator.isSelected()) {
            offersOptInLocator.click();
        }
    }

    public void setCustomerPrivacy(boolean customerPrivacy) {
        if (customerPrivacy && !customerPrivacyLocator.isSelected()) {
            customerPrivacyLocator.click();
        }
    }

    public void setNewsletterOptIn(boolean newsletterOptIn) {
        if (newsletterOptIn && !newsletterOptInLocator.isSelected()) {
            newsletterOptInLocator.click();
        }
    }

    public void setAcceptTerms(boolean acceptTerms) {
        if (acceptTerms && !acceptTermsLocator.isSelected()) {
            acceptTermsLocator.click();
        }
    }

    public void submitForm() {
        saveButtonLocator.click();
    }
}
