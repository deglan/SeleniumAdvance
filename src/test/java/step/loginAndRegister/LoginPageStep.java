package step.loginAndRegister;

import configuration.TestContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObject.base.BasePage;
import pageObject.loginAndRegister.LoginPage;


public class LoginPageStep extends BasePage {


    private LoginPage loginPage;

    public LoginPageStep(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public LoginPageStep(WebDriver driver) {
        super(driver);
    }

    public WebElement getErrorMessage() {
        return loginPage.getErrorMessage();
    }


    public void loginCorrect(TestContext testContext, WebDriver driver) {
        loginPage = new LoginPage(driver);
        loginPage.enterEmail(testContext.getProperty("user.email"));
        loginPage.enterPassword(testContext.getProperty("user.password"));
        loginPage.clickSignIn();
    }

    public void loginInCorrect(WebDriver driver) {
        loginPage = new LoginPage(driver);
        loginPage.enterEmail("invalid_user@example.com");
        loginPage.enterPassword("wrong_password");
        loginPage.clickSignIn();
    }
}
