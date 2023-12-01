package pageObject.product;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class ProductElementMiniature {

    private WebElement baseElement;

    @FindBy(css = ".h3.product-title a")
    private WebElement titleElement;

    @FindBy(css = ".price")
    private WebElement price;

    public ProductElementMiniature(WebElement baseElement) {
        PageFactory.initElements(new DefaultElementLocatorFactory(baseElement), this);
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
}
