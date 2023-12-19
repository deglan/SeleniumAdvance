package pageObject.category;

import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObject.category.util.FilterDirection;

import java.math.BigDecimal;

public class FilterSection extends CategoryBasePage {

    @FindBy(css = "button.js-search-filters-clear-all")
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

    public void setPriceRange(BigDecimal from, BigDecimal to) {
        changeFilterScope(from, FilterDirection.FROM.getMessage());
        changeFilterScope(to, FilterDirection.TO.getMessage());
    }

    private void changeFilterScope(BigDecimal targetPrice, String type) {
        WebElement filterBar = type.equals(FilterDirection.FROM.getMessage()) ? filterBarLeft : filterBarRight;
        Keys direction = type.equals(FilterDirection.FROM.getMessage()) ? Keys.ARROW_RIGHT : Keys.ARROW_LEFT;

        int currentLoop = 0;
        while (checkFilterChangeDirection(targetPrice, type) && (currentLoop < (Integer) testContext.getProperty("max-loop"))) {
            waitForSpinner(filterBar, direction);
            currentLoop++;
        }
    }

    private boolean checkFilterChangeDirection(BigDecimal targetPrice, String type) {
        return type.equals(FilterDirection.FROM.getMessage()) ?
                getPrice(type).compareTo(targetPrice) < 0 : getPrice(type).compareTo(targetPrice) > 0;
    }

    public void waitForSpinner(WebElement element, Keys direction){
        try{
            element.sendKeys(direction);
            wait.until(ExpectedConditions.invisibilityOf(spinner));
        }catch (StaleElementReferenceException e) {
            wait.until(ExpectedConditions.stalenessOf(element));
            element.sendKeys(direction);
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
        click(clearFilter);
        wait.until(ExpectedConditions.invisibilityOf(clearFilter));
    }
}
