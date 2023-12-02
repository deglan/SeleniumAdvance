package pageObject.product;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

@AllArgsConstructor
@NoArgsConstructor
public class ProductElementMiniature {

    private WebElement productElement;

    @FindBy(css = ".h3.product-title a")
    private WebElement titleElement;

    @FindBy(css = ".price")
    private WebElement price;

    public ProductElementMiniature(WebElement productElement) {
        this.productElement = productElement;
        PageFactory.initElements(new DefaultElementLocatorFactory(productElement), this);
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
            titleElement.click();
        }
    }
}
