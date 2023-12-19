package step.base;

import configuration.TestContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.time.Duration;

public class StepBase {

    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public TestContext testContext;

    public StepBase(WebDriver driver, WebElement element) {
        initDriver(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(element), this);
    }

    public StepBase(WebDriver driver) {
        initDriver(driver);
        PageFactory.initElements(driver, this);
    }

    private void initDriver(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.testContext = TestContext.getInstance();
        Integer waitInSeconds = (Integer) testContext.getProperty("wait");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(waitInSeconds.longValue()));
    }

    public void click(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            actions.scrollToElement(element);
            actions.scrollByAmount(10,10);
            element.click();
        }

    }

    public void sendKeys(WebElement element, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        if (!element.getText().isEmpty()) {
            element.sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
        }
        if (!element.getText().isEmpty()) {
            element.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        }
        element.sendKeys(text);
    }

    public BigDecimal getBigDecimalFromElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        String elementText = element.getText().replace("$", "").trim();
        return new BigDecimal(elementText);
    }


}
