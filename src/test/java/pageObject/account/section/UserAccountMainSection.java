package pageObject.account.section;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserAccountMainSection {

    private WebDriver driver;

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
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openInformation() {
        informationLink.click();
    }

    public void openAddresses() {
        addressesLink.click();
    }

    public void openOrderHistory() {
        orderHistoryLink.click();
    }

    public void openCreditSlips() {
        creditSlipsLink.click();
    }

    public void openGDPR() {
        gdprLink.click();
    }

    public void signOut() {
        signOutLink.click();
    }
}
