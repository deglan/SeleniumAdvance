package tests.checkout;

import base.UrlProvider;
import driver.DriverSetUp;
import model.Basket;
import org.junit.jupiter.api.Test;
import pageObject.account.UserAccountPage;
import pageObject.account.section.OrderHistorySection;
import pageObject.basket.BasketPage;
import pageObject.checkout.CheckoutPage;
import pageObject.checkout.section.CompleteOrderSection;
import pageObject.checkout.section.PaymentSection;
import pageObject.checkout.section.ShippingSection;
import pageObject.home.HomePage;
import pageObject.product.CartModal;
import utils.basket.BasketHandler;
import utils.checkout.CheckoutAddressFormHandler;
import utils.loginAndRegister.LoginPageHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutTest extends DriverSetUp {
    private LoginPageHandler loginPageHandler = new LoginPageHandler();
    private CheckoutAddressFormHandler addressFormHandler = new CheckoutAddressFormHandler(driver);
    private UserAccountPage userAccountPage = new UserAccountPage(driver);
    private OrderHistorySection orderHistorySection = new OrderHistorySection(driver);

    @Test
    public void shouldCreateFullOrder() {

        driver.get(UrlProvider.LOGIN_URL.getUrl());
        loginPageHandler.loginCorrect(testContext, driver);

        assertEquals(UrlProvider.LOGGED_USER_URL.getUrl(), driver.getCurrentUrl());

        HomePage homePage = new HomePage(driver);
        Basket testBasket = new Basket();

        BasketHandler.addRandomProductToBasket(driver, homePage, testBasket);

        CartModal cartModal = new CartModal(driver);
        cartModal.proceedToCheckout();

        BasketPage basketPage = new BasketPage(driver);
        basketPage.proceedToCheckout();

        CheckoutPage addressPage = new CheckoutPage(driver);
        addressPage.clickDifferentInvoiceAddress();
        addressPage.clickAddNewInvoiceAddress();

        addressFormHandler.changeCountry("Poland");

        addressFormHandler.fillAndSubmitAddressForm(testContext, driver);
        ShippingSection shippingSection = new ShippingSection(driver);
        shippingSection.clickContinue();

        PaymentSection paymentSection = new PaymentSection(driver);
        paymentSection.selectPayByCheck();
        paymentSection.agreeToTermsAndConditions();
        paymentSection.clickPlaceOrder();

        CompleteOrderSection completeOrderSection = new CompleteOrderSection(driver);
        String orderReference = completeOrderSection.getOrderReference();

        userAccountPage.openAccountDetails();


        userAccountPage.openHistoryDetails();
        assertTrue(orderHistorySection.isOrderPresent(orderReference));

    }
}
