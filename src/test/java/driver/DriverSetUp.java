package driver;


import configuration.TestContext;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.base.BasePage;
import step.base.StepBase;

import java.time.Duration;


public class DriverSetUp {

    protected static TestContext testContext = TestContext.getInstance();
    protected WebDriver driver;
    protected WebDriverWait wait;


    @BeforeAll
    public static void setUpClass() {
        testContext.loadConfigurations("src/test/resources/");
    }

    @BeforeEach
    public void setup() {
        String browser = testContext.getProperty("browser");
        driver = BrowserStrategy.valueOf(browser.toUpperCase()).getDriver();
        Integer waitInSeconds = (Integer) testContext.getProperty("wait");
        if (waitInSeconds != null) {
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(waitInSeconds.longValue()));
        } else {
            throw new IllegalStateException("The 'wait' property in testContext is not set.");
        }
    }

    @AfterEach
    public void closeDriver() {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public <T extends BasePage> T at(Class<T> pageType) {
        return pageType.getDeclaredConstructor(WebDriver.class).newInstance(driver);
    }

    @SneakyThrows
    public <T extends StepBase> T with(Class<T> step) {
        return step.getDeclaredConstructor(WebDriver.class).newInstance(driver);
    }
}

