package pageObject.product;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import pageObject.base.BasePage;


public class ProductElementMiniature extends BasePage {

    @FindBy(css = ".h3.product-title a")
    private WebElement titleElement;

    @FindBy(css = ".price")
    private WebElement price;

    public ProductElementMiniature(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public ProductElementMiniature(WebDriver driver) {
        super(driver);
    }


    public String getTitle() {
        return titleElement.getText().trim();
    }

    public String getPrice() {
        return price.getText();
    }

    public boolean isTitleMatching(String productName) {
        return getTitle().equalsIgnoreCase(productName);
    }

    public void selectProduct(String desiredProductName) {
        if (getTitle().equalsIgnoreCase(desiredProductName)) {
            click(titleElement);
        }
    }
}
