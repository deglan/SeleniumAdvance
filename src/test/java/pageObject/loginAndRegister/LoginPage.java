package pageObject.loginAndRegister;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class LoginPage {

    private WebDriver driver;

    @FindBy(name = "email")
    private WebElement emailInput;
    @FindBy(name = "password")
    private WebElement passwordInput;
    @FindBy(id = "submit-login")
    private WebElement signInButton;
    @FindBy(linkText = "Forgot your password?")
    private WebElement forgotPasswordLink;
    @FindBy(linkText = "No account? Create one here")
    private WebElement createAccountLink;
    @FindBy(css = ".alert.alert-danger")
    private WebElement errorMessage;
    @FindBy(name = "email")
    private WebElement resetEmailInput;
    @FindBy(css = ".ps-alert-success")
    private WebElement resetPasswordSuccessMessage;
    @FindBy(css = "button.form-control-submit")
    private WebElement sendResetLinkButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }
    public void enterResetEmail(String email) {
        resetEmailInput.sendKeys(email);
    }

    public void clickSignIn() {
        signInButton.click();
    }

    public void clickCreateAccount() {
        createAccountLink.click();
    }

    public void clickSendResetLink() {
        sendResetLinkButton.click();
    }
    public void clickForgotPassword() {
        forgotPasswordLink.click();
    }
}
