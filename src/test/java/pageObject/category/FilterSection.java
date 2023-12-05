package pageObject.category;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.math.BigDecimal;

public class FilterSection extends CategoryBasePage {

    @FindBy(css = "#_desktop_search_filters_clear_all > button")
    private WebElement clearFilter;

    @FindBy(css = "div[id*='slider-range'] > a:nth-child(2)")
    private WebElement filterBarLeft;

    @FindBy(css = "div[id*='slider-range'] > a:nth-child(3)")
    private WebElement filterBarRight;

    @FindBy(css = "[id^='facet_label_']")
    private WebElement filterPrice;

    @FindBy(css = "div.faced-overlay")
    private WebElement spinner;

    public FilterSection(WebDriver driver) {
        super(driver);
    }

    public void setPrice(BigDecimal from, BigDecimal to) {
        adjustPrice(from, "FROM");
        adjustPrice(to, "TO");
    }

    private void adjustPrice(BigDecimal targetPrice, String type) {
        WebElement filterBar = type.equals("FROM") ? filterBarLeft : filterBarRight;
        Keys direction = type.equals("FROM") ? Keys.ARROW_RIGHT : Keys.ARROW_LEFT;

        while (type.equals("FROM") ? getPrice(type).compareTo(targetPrice) < 0 : getPrice(type).compareTo(targetPrice) > 0) {
            filterBar.sendKeys(direction);
            wait.until(ExpectedConditions.invisibilityOf(spinner));
        }
    }

    public BigDecimal getPrice(String value) {
        try {
            actions.scrollToElement(filterPrice);
            String[] prices = filterPrice.getText().split("-");
            return new BigDecimal(prices[value.equals("FROM") ? 0 : 1].replaceAll("[$,]", "").trim());
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    public void clearFilter() {
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(clearFilter)));
        clearFilter.click();
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.invisibilityOf(clearFilter)));
    }
}
