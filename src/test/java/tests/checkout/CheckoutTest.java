package tests.checkout;

import base.UrlProvider;
import org.junit.jupiter.api.Test;
import pageObject.account.UserAccountPage;
import pageObject.account.section.OrderHistorySection;
import pageObject.basket.BasketPage;
import pageObject.checkout.CheckoutPage;
import pageObject.checkout.section.CompleteOrderSection;
import pageObject.checkout.section.PaymentSection;
import pageObject.checkout.section.ShippingSection;
import pageObject.product.CartModal;
import step.basket.BasketStep;
import step.checkout.CheckoutAddressFormStep;
import step.loginAndRegister.LoginPageStep;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class CheckoutTest extends CheckoutSetUp {

    @Test
    public void shouldCreateFullOrder() {

        driver.get(UrlProvider.LOGIN_URL.getUrl());
        at(LoginPageStep.class).loginCorrect(testContext, driver);

        assertEquals(UrlProvider.LOGGED_USER_URL.getUrl(), driver.getCurrentUrl());

        with(BasketStep.class).addRandomProductToBasket(expectedBasket);

        at(CartModal.class).proceedToCheckout();

        at(BasketPage.class).proceedToCheckout();

        at(CheckoutPage.class)
                .clickDifferentInvoiceAddress();

        at(CheckoutPage.class)
                .clickAddNewInvoiceAddress();

        at(CheckoutAddressFormStep.class)
                .changeCountry("Poland");

        at(CheckoutAddressFormStep.class)
                .fillAndSubmitAddressForm(testContext, driver);

        at(ShippingSection.class).clickContinue();

        at(PaymentSection.class).selectPayByCheck();
        at(PaymentSection.class).agreeToTermsAndConditions();
        at(PaymentSection.class).clickPlaceOrder();

        Optional<String> orderReference = at(CompleteOrderSection.class).getOrderReference();

        assertTrue(orderReference.isPresent(), "Order reference is not present");

        at(UserAccountPage.class)
                .openAccountDetails();

        at(UserAccountPage.class).openHistoryDetails();
        assertThat(orderReference.get())
                .isIn(at(OrderHistorySection.class)
                        .getOrderReference(orderReference.get()));

    }
}
