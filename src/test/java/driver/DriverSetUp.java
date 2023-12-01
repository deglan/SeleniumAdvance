package driver;


import configuration.TestContext;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverSetUp {

    protected static TestContext testContext = TestContext.getInstance();
    protected static WebDriver driver;


    @BeforeAll
    public static void setUpClass() {
        testContext.loadConfigurations("src/test/resources/");
        String browser = testContext.getProperty("browser");
        driver = BrowserStrategy.valueOf(browser.toUpperCase()).getDriver();
    }

    @BeforeEach
    public void setup() {
        Cookie sessionCookie = TestContext.getInstance().getCookie();
        if (sessionCookie != null) {
            driver.manage().addCookie(sessionCookie);
        }
    }

    @AfterEach
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
