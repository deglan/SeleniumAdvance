package tests.loginAndRegister;

import base.UrlProvider;
import formHandler.loginAndRegister.RegisterPageHandler;
import org.junit.jupiter.api.Test;
import driver.DriverSetUp;
import pageObject.loginAndRegister.RegisterPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class RegisterTest extends DriverSetUp {


    private RegisterPageHandler registerPageHandler = new RegisterPageHandler();

    @Test
    public void shouldRegisterCorrectly() {
        driver.get(UrlProvider.REGISTER_URL.getUrl());

        registerPageHandler.fillUpRegistrationCorrect(driver);

        assertEquals(UrlProvider.HOME_URL.getUrl(), driver.getCurrentUrl());
    }

    @Test
    public void shouldShowErrorForAlreadyRegisteredUser() {
        driver.get(UrlProvider.REGISTER_URL.getUrl());
        registerPageHandler.fillUpRegistrationWithRegisteredUser(driver);

        RegisterPage registerPage = new RegisterPage(driver);
        assertTrue(registerPage.getErrorMessage().isDisplayed(),
                "Error message should be displayed for already registered user");
    }

}