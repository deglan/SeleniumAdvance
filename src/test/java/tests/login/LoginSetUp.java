package tests.login;

import base.UrlProvider;
import driver.DriverSetUp;
import org.junit.jupiter.api.BeforeEach;
import pageObject.loginAndRegister.LoginPage;
import utils.loginAndRegister.LoginPageHandler;

public class LoginSetUp extends DriverSetUp {

    LoginPageHandler loginPageHandler;
    LoginPage loginPage;

    @BeforeEach
    public void setUpLogin() {
        loginPageHandler = new LoginPageHandler();
        loginPage = new LoginPage(driver);
        driver.get(UrlProvider.LOGIN_URL.getUrl());
    }
}
