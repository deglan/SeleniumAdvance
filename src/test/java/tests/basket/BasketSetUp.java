package tests.basket;

import driver.DriverSetUp;
import model.Basket;
import org.junit.jupiter.api.BeforeEach;
import pageObject.basket.BasketPage;

public class BasketSetUp extends DriverSetUp {

    Basket expectedBasket;

    @BeforeEach
    public void setUpBasket() {
        expectedBasket = new Basket();
    }
}
