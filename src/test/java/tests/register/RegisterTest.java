package tests.register;

import base.UrlProvider;
import driver.DriverSetUp;
import org.junit.jupiter.api.Test;
import pageObject.loginAndRegister.RegisterPage;
import step.loginAndRegister.RegisterPageStep;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class RegisterTest extends DriverSetUp {

    @Test
    public void shouldRegisterCorrectly() {
        driver.get(UrlProvider.REGISTER_URL.getUrl());
        at(RegisterPageStep.class).fillUpRegistrationCorrect();
        assertEquals(UrlProvider.HOME_URL.getUrl(), driver.getCurrentUrl());
    }

    @Test
    public void shouldShowErrorForAlreadyRegisteredUser() {
        driver.get(UrlProvider.REGISTER_URL.getUrl());
        at(RegisterPageStep.class).fillUpRegistrationWithRegisteredUser(driver);
        assertThat(at(RegisterPage.class).getErrorMessage().isDisplayed())
                .as("Error message should be displayed for already registered user")
                .isTrue();
    }

}