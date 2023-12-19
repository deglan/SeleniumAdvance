package tests.login;

import base.UrlProvider;
import driver.DriverSetUp;
import org.junit.jupiter.api.Test;
import pageObject.loginAndRegister.LoginPage;
import step.loginAndRegister.LoginPageStep;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoginTest extends DriverSetUp {


    @Test
    public void shouldLoginCorrectly() {
        driver.get(UrlProvider.LOGIN_URL.getUrl());
        at(LoginPageStep.class).loginCorrect(testContext, driver);
        assertEquals(UrlProvider.LOGGED_USER_URL.getUrl(), driver.getCurrentUrl());
    }


    @Test
    public void shouldLoginIncorrect() {

        at(LoginPageStep.class).loginInCorrect(driver);
        assertThat(at(LoginPageStep.class)
                .getErrorMessage().isDisplayed())
                .as("Error message should be displayed")
                .isTrue();
    }

    @Test
    public void testForgotPassword() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        at(LoginPage.class).clickForgotPassword();
        at(LoginPage.class).enterResetEmail(testContext.getProperty("user.resetEmail"));
        at(LoginPage.class).clickSendResetLink();

        assertThatCode(() -> {
            assertThat(at(LoginPage.class).getResetPasswordSuccessMessage().isDisplayed())
                    .as("Success message should be displayed")
                    .isTrue();

            assertThat(driver.getCurrentUrl())
                    .as("Check if the current URL is correct")
                    .isEqualTo(UrlProvider.FORGOT_PASSWORD_URL.getUrl());
        }).doesNotThrowAnyException();
    }
}
