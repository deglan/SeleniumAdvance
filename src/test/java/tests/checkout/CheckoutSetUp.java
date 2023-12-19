package tests.checkout;

import driver.DriverSetUp;
import model.Basket;
import org.junit.jupiter.api.BeforeEach;

public class CheckoutSetUp extends DriverSetUp {
    Basket expectedBasket;

    @BeforeEach
    public void setUpCheckout() {
        expectedBasket = new Basket();
    }
}
