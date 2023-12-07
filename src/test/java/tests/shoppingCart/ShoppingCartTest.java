package tests.shoppingCart;

import base.UrlProvider;
import configuration.TestContext;
import driver.DriverSetUp;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pageObject.category.FilterSection;
import pageObject.category.ProductCategoryPage;
import pageObject.home.sections.HeaderSection;
import pageObject.product.CartModal;
import pageObject.product.ProductElementMiniature;
import pageObject.product.ProductPage;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShoppingCartTest extends ShoppingCartSetUp {

    @Test
    public void shouldAddToShoppingCart() {
        driver.get(UrlProvider.HOME_URL.getUrl());

        categoryPage.selectCategory(testContext.getProperty("shoppingCartTest.selectCategory"));
        WebElement productElement = categoryPage.getProductElement(testContext.getProperty("shoppingCartTest.getProductElement"));
        ProductElementMiniature productElementMiniature = new ProductElementMiniature(productElement);
        productElementMiniature.selectProduct(testContext.getProperty("shoppingCartTest.getProductElement"));

        productPage.setQuantity(testContext.getProperty("shoppingCartTest.setQuantity"));
        productPage.addToCart();

        BigDecimal expectedPrice = new BigDecimal(testContext.getProperty("shoppingCartTest.expectedPrice").toString());
        BigDecimal expectedSubtotal = new BigDecimal(testContext.getProperty("shoppingCartTest.expectedSubtotal").toString());
        BigDecimal expectedTotal = new BigDecimal(testContext.getProperty("shoppingCartTest.expectedTotal").toString());
        assertEquals(0, expectedPrice.compareTo(productPage.getProductPrice()));
        assertEquals(0, expectedSubtotal.compareTo(cartModal.getSubtotal()));
        assertEquals(0, expectedTotal.compareTo(cartModal.getTotal()));

        cartModal.continueShopping();
        int cartCount = headerSection.getCartCount();

        assertEquals(3, cartCount);
    }
}
