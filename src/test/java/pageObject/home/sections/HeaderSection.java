package pageObject.home.sections;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderSection {

    private WebDriver driver;

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
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnDesktopLogo() {
        desktopLogo.click();
    }

    public void goToContactUsPage() {
        contactUsLink.click();
    }

    public void signOut() {
        signOutLink.click();
    }

    public void goToAccountPage() {
        accountLink.click();
    }

    public int getCartCount() {
        String countText = cartProductsCount.getText();
        String numericCount = countText.replaceAll("[^0-9]", "");
        return Integer.parseInt(numericCount);
    }
}
