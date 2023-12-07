package tests.checkout;

import base.UrlProvider;
import driver.DriverSetUp;
import model.Basket;
import org.junit.jupiter.api.BeforeEach;
import pageObject.account.UserAccountPage;
import pageObject.account.section.OrderHistorySection;
import pageObject.basket.BasketPage;
import pageObject.checkout.CheckoutPage;
import pageObject.checkout.section.CompleteOrderSection;
import pageObject.checkout.section.PaymentSection;
import pageObject.checkout.section.ShippingSection;
import pageObject.home.HomePage;
import pageObject.product.CartModal;
import utils.checkout.CheckoutAddressFormHandler;
import utils.loginAndRegister.LoginPageHandler;

public class CheckoutSetUp extends DriverSetUp {

    LoginPageHandler loginPageHandler;
    CheckoutAddressFormHandler addressFormHandler;
    UserAccountPage userAccountPage;
    OrderHistorySection orderHistorySection;
    HomePage homePage;
    Basket testBasket;
    CartModal cartModal;
    BasketPage basketPage;
    CheckoutPage addressPage;
    ShippingSection shippingSection;
    PaymentSection paymentSection;
    CompleteOrderSection completeOrderSection;

    @BeforeEach
    public void setUpCheckout() {
        driver.get(UrlProvider.HOME_URL.getUrl());
        loginPageHandler = new LoginPageHandler();
        addressFormHandler = new CheckoutAddressFormHandler(driver);
        userAccountPage = new UserAccountPage(driver);
        orderHistorySection = new OrderHistorySection(driver);
        homePage = new HomePage(driver);
        testBasket = new Basket();
         cartModal = new CartModal(driver);
         basketPage = new BasketPage(driver);
         addressPage = new CheckoutPage(driver);
         shippingSection = new ShippingSection(driver);
         paymentSection = new PaymentSection(driver);
         completeOrderSection = new CompleteOrderSection(driver);
    }
}
