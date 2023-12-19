package tests.shoppingCart;

import base.UrlProvider;
import driver.DriverSetUp;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import pageObject.category.ProductCategoryPage;
import pageObject.home.sections.HeaderSection;
import pageObject.product.CartModal;
import pageObject.product.ProductPage;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartTest extends DriverSetUp {

    @Test
    public void shouldAddToShoppingCart() {
        driver.get(UrlProvider.HOME_URL.getUrl());

        at(ProductCategoryPage.class)
                .selectCategory(testContext.getProperty("shoppingCartTest.selectCategory").toString());
        at(ProductCategoryPage.class)
                .getProductElementMiniature(testContext.getProperty("shoppingCartTest.getProductElement").toString())
                .selectProduct(testContext.getProperty("shoppingCartTest.getProductElement").toString());

        at(ProductPage.class)
                .setQuantity(testContext.getProperty("shoppingCartTest.setQuantity"));
        at(ProductPage.class)
                .addToCart();

        BigDecimal expectedPrice = new BigDecimal(testContext.getProperty("shoppingCartTest.expectedPrice").toString());
        BigDecimal expectedSubtotal = new BigDecimal(testContext.getProperty("shoppingCartTest.expectedSubtotal").toString());
        BigDecimal expectedTotal = new BigDecimal(testContext.getProperty("shoppingCartTest.expectedTotal").toString());

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(at(ProductPage.class).getProductPrice())
                    .as("Check if product price is as expected")
                    .isEqualByComparingTo(expectedPrice);

            softAssertions.assertThat(at(CartModal.class).getSubtotal())
                    .as("Check if cart subtotal is as expected")
                    .isEqualByComparingTo(expectedSubtotal);

            softAssertions.assertThat(at(CartModal.class).getTotal())
                    .as("Check if cart total is as expected")
                    .isEqualByComparingTo(expectedTotal);
        });

        at(CartModal.class).continueShopping();

        assertEquals((Integer) testContext.getProperty("shoppingCartTest.setQuantity"), at(HeaderSection.class).getCartCount());
    }
}
