package pageObject.home.sections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FooterSection {

    private WebDriver driver;

    @FindBy(id = "footer")
    private WebElement footer;


    public FooterSection(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnFooterLink(String linkText) {
        footer.findElement(By.linkText(linkText)).click();
    }
}
