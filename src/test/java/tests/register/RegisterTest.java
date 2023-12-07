package tests.register;

import base.UrlProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class RegisterTest extends RegisterSetUp {

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
        assertTrue(registerPage.getErrorMessage().isDisplayed(),
                "Error message should be displayed for already registered user");
    }

}