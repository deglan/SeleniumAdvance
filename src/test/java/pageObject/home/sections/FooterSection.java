package pageObject.home.sections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObject.base.BasePage;

public class FooterSection extends BasePage {

    @FindBy(id = "footer")
    private WebElement footer;


    public FooterSection(WebDriver driver) {
        super(driver);
    }

    public void clickOnFooterLink(String linkText) {
        footer.findElement(By.linkText(linkText)).click();
    }
}
