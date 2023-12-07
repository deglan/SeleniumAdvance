package pageObject.loginAndRegister;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObject.base.BasePage;


public class RegisterPage extends BasePage {

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
        super(driver);
    }

    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("Mr")) {
            genderMr.click();
        } else if (gender.equalsIgnoreCase("Mrs")) {
            genderMrs.click();
        }
    }

    public void enterFirstName(String firstName) {
        sendKeys(firstNameLocator, firstName);
    }

    public void enterLastName(String lastName) {
        sendKeys(lastNameLocator, lastName);
    }

    public void enterEmail(String email) {
        sendKeys(emailLocator, email);
    }

    public void enterPassword(String password) {
        sendKeys(passwordLocator, password);
    }

    public void enterBirthdate(String birthdate) {
        sendKeys(birthdateLocator, birthdate);
    }

    public void setOffersOptIn(boolean offersOptIn) {
        offersOptInLocator.click();
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
        click(saveButtonLocator);
    }
}
