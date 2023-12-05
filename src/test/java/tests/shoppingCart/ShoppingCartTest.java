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

public class ShoppingCartTest extends DriverSetUp {

    @Test
    public void shouldAddToShoppingCart() {
        driver.get(UrlProvider.HOME_URL.getUrl());
        
        ProductCategoryPage categoryPage = new ProductCategoryPage(driver);
        categoryPage.selectCategory(testContext.getProperty("shoppingCartTest.selectCategory"));
        WebElement productElement = categoryPage.getProductElement(testContext.getProperty("shoppingCartTest.getProductElement"));
        ProductElementMiniature productElementMiniature = new ProductElementMiniature(productElement);
        productElementMiniature.selectProduct(testContext.getProperty("shoppingCartTest.getProductElement"));

        ProductPage productPage = new ProductPage(driver);
        productPage.setQuantity(testContext.getProperty("shoppingCartTest.setQuantity"));
        productPage.addToCart();

        CartModal cartModal = new CartModal(driver);
        BigDecimal expectedPrice = new BigDecimal(testContext.getProperty("shoppingCartTest.expectedPrice").toString());
        BigDecimal expectedSubtotal = new BigDecimal(testContext.getProperty("shoppingCartTest.expectedSubtotal").toString());
        BigDecimal expectedTotal = new BigDecimal(testContext.getProperty("shoppingCartTest.expectedTotal").toString());

        assertEquals(0, expectedPrice.compareTo(productPage.getProductPrice()));
        assertEquals(0, expectedSubtotal.compareTo(cartModal.getSubtotal()));
        assertEquals(0, expectedTotal.compareTo(cartModal.getTotal()));

        cartModal.continueShopping();

        HeaderSection headerSection = new HeaderSection(driver);
        int cartCount = headerSection.getCartCount();
        assertEquals(3, cartCount);
    }
}
