package pageObject.account.section;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class OrderRowComponent {

    private final WebElement row;

    public OrderRowComponent(WebElement row) {
        this.row = row;
    }

    public String getReference() {
        return row.findElement(By.cssSelector("th")).getText();
    }

    public String getDate() {
        return row.findElement(By.cssSelector("td:nth-child(2)")).getText();
    }

    public String getTotal() {
        return row.findElement(By.cssSelector("td.text-xs-right")).getText();
    }

    public String getPayment() {
        return row.findElement(By.cssSelector("td.hidden-md-down")).getText();
    }

    public String getStatus() {
        return row.findElement(By.cssSelector("td:nth-child(5) span")).getText();
    }
}
