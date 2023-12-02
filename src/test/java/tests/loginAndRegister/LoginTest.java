package tests.loginAndRegister;

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


public class LoginTest extends DriverSetUp {
    private LoginPageHandler loginPageHandler = new LoginPageHandler();


    @Test
    public void shouldLoginCorrectly() {

        driver.get(UrlProvider.LOGIN_URL.getUrl());
        loginPageHandler.loginCorrect(testContext, driver);

        assertEquals(UrlProvider.LOGGED_USER_URL.getUrl(), driver.getCurrentUrl());

        Cookie sessionCookiePrestaShop = driver.manage().getCookieNamed("PrestaShop-1c6beeb2122e9a8fd46d17038d1c8c6a");
        Cookie sessionCookiePHPSESSID = driver.manage().getCookieNamed("PHPSESSID");

        TestContext.getInstance().setCookie(sessionCookiePrestaShop);
        TestContext.getInstance().setCookie(sessionCookiePHPSESSID);
    }


    @Test
    public void shouldLoginIncorrect() {
        driver.get(UrlProvider.LOGIN_URL.getUrl());
        loginPageHandler.loginInCorrect(driver);
        assertThat(loginPageHandler.getErrorMessage().isDisplayed())
                .as("Error message should be displayed")
                .isTrue();  }

    @Test
    public void testForgotPassword() {
        driver.get(UrlProvider.LOGIN_URL.getUrl());
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickForgotPassword();
        loginPage.enterResetEmail(testContext.getProperty("user.resetEmail"));
        loginPage.clickSendResetLink();

        assertThatCode(() -> {
            assertThat(loginPage.getResetPasswordSuccessMessage().isDisplayed())
                    .as("Success message should be displayed")
                    .isTrue();

            assertThat(driver.getCurrentUrl())
                    .as("Check if the current URL is correct")
                    .isEqualTo(UrlProvider.BASKET_URL.getUrl());
        }).doesNotThrowAnyException();
    }
}
