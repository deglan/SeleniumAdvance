package pageObject.account.section;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObject.base.BasePage;

public class UserAccountMainSection extends BasePage {

    @FindBy(id = "identity-link")
    private WebElement informationLink;

    @FindBy(id = "addresses-link")
    private WebElement addressesLink;

    @FindBy(id = "history-link")
    private WebElement orderHistoryLink;

    @FindBy(id = "order-slips-link")
    private WebElement creditSlipsLink;

    @FindBy(id = "psgdpr-link")
    private WebElement gdprLink;

    @FindBy(css = "a[href*='mylogout']")
    private WebElement signOutLink;

    public UserAccountMainSection(WebDriver driver) {
        super(driver);
    }

    public void openInformation() {
        click(informationLink);
    }

    public void openAddresses() {
        click(addressesLink);
    }

    public void openOrderHistory() {
        click(orderHistoryLink);
    }

    public void openCreditSlips() {
        click(creditSlipsLink);
    }

    public void openGDPR() {
        click(gdprLink);
    }

    public void signOut() {
        click(signOutLink);
    }
}
