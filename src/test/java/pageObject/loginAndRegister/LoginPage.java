package pageObject.loginAndRegister;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObject.base.BasePage;

@Getter
public class LoginPage extends BasePage {

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
        super(driver);
    }

    public void enterEmail(String email) {
        sendKeys(emailInput, email);
    }

    public void enterPassword(String password) {
        sendKeys(passwordInput, password);
    }

    public void enterResetEmail(String email) {
        sendKeys(resetEmailInput, email);
    }

    public void clickSignIn() {
        click(signInButton);
    }

    public void clickCreateAccount() {
        click(createAccountLink);
    }

    public void clickSendResetLink() {
        click(sendResetLinkButton);
    }

    public void clickForgotPassword() {
        click(forgotPasswordLink);
    }
}
