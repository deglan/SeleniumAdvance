package pageObject.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObject.account.section.OrderHistorySection;
import pageObject.account.section.UserAccountMainSection;
import pageObject.base.BasePage;
import pageObject.home.sections.HeaderSection;

public class UserAccountPage extends BasePage {

    private UserAccountMainSection userAccountMainSection;
    private OrderHistorySection orderHistorySection;

    @FindBy(css = "a.account")
    private WebElement accountButton;

    public UserAccountPage(WebDriver driver) {
        super(driver);
        this.userAccountMainSection = new UserAccountMainSection(driver);
        this.orderHistorySection = new OrderHistorySection(driver);
    }


    public void openAccountDetails() {
        click(accountButton);
    }

    public void openHistoryDetails() {
        userAccountMainSection.openOrderHistory();
        orderHistorySection.getOrders();
    }
}
