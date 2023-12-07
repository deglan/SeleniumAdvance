package tests.login;

import base.UrlProvider;
import configuration.TestContext;
import driver.DriverSetUp;
import utils.loginAndRegister.LoginPageHandler;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import pageObject.loginAndRegister.LoginPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoginTest extends LoginSetUp {


    @Test
    public void shouldLoginCorrectly() {
        loginPageHandler.loginCorrect(testContext, driver);
        assertEquals(UrlProvider.LOGGED_USER_URL.getUrl(), driver.getCurrentUrl());
    }


    @Test
    public void shouldLoginIncorrect() {

        loginPageHandler.loginInCorrect(driver);
        assertThat(loginPageHandler.getErrorMessage().isDisplayed())
                .as("Error message should be displayed")
                .isTrue();  }

    @Test
    public void testForgotPassword() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        loginPage.clickForgotPassword();
        loginPage.enterResetEmail(testContext.getProperty("user.resetEmail"));
        loginPage.clickSendResetLink();

        assertThatCode(() -> {
            assertThat(loginPage.getResetPasswordSuccessMessage().isDisplayed())
                    .as("Success message should be displayed")
                    .isTrue();

            assertThat(driver.getCurrentUrl())
                    .as("Check if the current URL is correct")
                    .isEqualTo(UrlProvider.FORGOT_PASSWORD_URL.getUrl());
        }).doesNotThrowAnyException();
    }
}
