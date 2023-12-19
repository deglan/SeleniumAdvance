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

    public List<String> getOrderReference(String orderReference) {
        return this.getOrders().stream()
                .map(OrderDetails::getReference)
                .collect(Collectors.toList());
    }

    private OrderDetails createOrderDetailsFromRow(WebElement row) {
        OrderRowComponent orderRowComponent = new OrderRowComponent(row);
        String reference = orderRowComponent.getReference();
        String date = orderRowComponent.getDate();
        String total = orderRowComponent.getTotal();
        String payment = orderRowComponent.getPayment();
        String status = orderRowComponent.getStatus();
        return new OrderDetails(reference, date, total, payment, status);
    }
}
