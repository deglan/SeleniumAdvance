package tests.register;

import driver.DriverSetUp;
import org.junit.jupiter.api.BeforeEach;
import pageObject.loginAndRegister.RegisterPage;
import utils.loginAndRegister.RegisterPageHandler;

public class RegisterSetUp extends DriverSetUp {

    RegisterPageHandler registerPageHandler;
    RegisterPage registerPage;

    @BeforeEach
    public void setUpRegister() {
         registerPageHandler = new RegisterPageHandler();
         registerPage = new RegisterPage(driver);
    }
}
