package pageObject.home.sections;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObject.base.BasePage;

public class HeaderSection extends BasePage {

    @FindBy(id = "_desktop_logo")
    private WebElement desktopLogo;
    @FindBy(id = "contact-link")
    private WebElement contactUsLink;
    @FindBy(css = "a.logout")
    private WebElement signOutLink;
    @FindBy(css = "a.account")
    private WebElement accountLink;
    @FindBy(css = ".cart-products-count")
    private WebElement cartProductsCount;

    public HeaderSection(WebDriver driver) {
        super(driver);
    }

    public void clickOnDesktopLogo() {
        click(desktopLogo);
    }

    public void goToContactUsPage() {
        click(contactUsLink);
    }

    public void signOut() {
        click(signOutLink);
    }

    public void goToAccountPage() {
        click(accountLink);
    }

    public int getCartCount() {
        String countText = cartProductsCount.getText();
        String numericCount = countText.replaceAll("[^0-9]", "");
        return Integer.parseInt(numericCount);
    }
}
