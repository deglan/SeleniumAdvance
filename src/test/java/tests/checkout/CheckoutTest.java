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

public class CheckoutTest extends CheckoutSetUp {

    @Test
    public void shouldCreateFullOrder() {

        driver.get(UrlProvider.LOGIN_URL.getUrl());
        loginPageHandler.loginCorrect(testContext, driver);

        assertEquals(UrlProvider.LOGGED_USER_URL.getUrl(), driver.getCurrentUrl());

        BasketHandler.addRandomProductToBasket(driver, homePage, testBasket);


        cartModal.proceedToCheckout();


        basketPage.proceedToCheckout();


        addressPage.clickDifferentInvoiceAddress();
        addressPage.clickAddNewInvoiceAddress();

        addressFormHandler.changeCountry("Poland");

        addressFormHandler.fillAndSubmitAddressForm(testContext, driver);

        shippingSection.clickContinue();


        paymentSection.selectPayByCheck();
        paymentSection.agreeToTermsAndConditions();
        paymentSection.clickPlaceOrder();

        String orderReference = completeOrderSection.getOrderReference();

        userAccountPage.openAccountDetails();

        userAccountPage.openHistoryDetails();
        assertTrue(orderHistorySection.isOrderPresent(orderReference));

    }
}
