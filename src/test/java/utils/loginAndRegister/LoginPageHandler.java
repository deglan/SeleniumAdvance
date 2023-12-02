package utils.loginAndRegister;

import configuration.TestContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObject.loginAndRegister.LoginPage;


public class LoginPageHandler {


    private LoginPage loginPage;

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
