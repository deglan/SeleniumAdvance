package pageObject.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObject.account.section.OrderHistorySection;
import pageObject.account.section.UserAccountMainSection;
import pageObject.home.sections.HeaderSection;

public class UserAccountPage {

    private WebDriver driver;

    private UserAccountMainSection userAccountMainSection;
    private OrderHistorySection orderHistorySection;

    @FindBy(css = "a.account")
    private WebElement accountButton;

    public UserAccountPage(WebDriver driver) {
        this.driver = driver;
        this.userAccountMainSection = new UserAccountMainSection(driver);
        this.orderHistorySection = new OrderHistorySection(driver);
        PageFactory.initElements(driver, this);
    }


    public void openAccountDetails() {
        accountButton.click();

    }

    public void openHistoryDetails() {
        userAccountMainSection.openOrderHistory();
        orderHistorySection.getOrders();
    }
}
