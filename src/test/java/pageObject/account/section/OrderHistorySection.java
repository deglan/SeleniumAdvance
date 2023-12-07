package pageObject.account.section;

import model.OrderDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObject.base.BasePage;

import java.util.List;
import java.util.stream.Collectors;

public class OrderHistorySection extends BasePage {

    @FindBy(css = "table.table.table-striped.table-bordered tbody tr")
    private List<WebElement> orderRows;

    public OrderHistorySection(WebDriver driver) {
        super(driver);
    }

    public List<OrderDetails> getOrders() {
        return orderRows.stream().map(this::createOrderDetailsFromRow).collect(Collectors.toList());
    }

    private OrderDetails createOrderDetailsFromRow(WebElement row) {
        String reference = row.findElement(By.cssSelector("th")).getText();
        String date = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
        String total = row.findElement(By.cssSelector("td.text-xs-right")).getText();
        String payment = row.findElement(By.cssSelector("td.hidden-md-down")).getText();
        String status = row.findElement(By.cssSelector("td:nth-child(5) span")).getText();

        return new OrderDetails(reference, date, total, payment, status);
    }

    public boolean isOrderPresent(String orderReference) {
        return getOrders().stream().anyMatch(order -> order.getReference().equals(orderReference));
    }
}
