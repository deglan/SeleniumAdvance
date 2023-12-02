package tests.shoppingCart;

import base.UrlProvider;
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

public class ShoppingCartTest extends DriverSetUp {

    @Test
    public void shouldAddToShoppingCart() {
        driver.get(UrlProvider.HOME_URL.getUrl());
        
        ProductCategoryPage categoryPage = new ProductCategoryPage(driver);
        categoryPage.selectCategory("Art");
        WebElement productElement = categoryPage.getProductElement("THE BEST IS YET POSTER");
        ProductElementMiniature productElementMiniature = new ProductElementMiniature(productElement);
        productElementMiniature.selectProduct("THE BEST IS YET POSTER");

        ProductPage productPage = new ProductPage(driver);
        productPage.setQuantity(3);
        productPage.addToCart();

        CartModal cartModal = new CartModal(driver);
        BigDecimal expectedPrice = new BigDecimal("29.00");
        BigDecimal expectedSubtotal = new BigDecimal("87.00");
        BigDecimal expectedTotal = new BigDecimal("94.00");

        assertEquals(expectedPrice, productPage.getProductPrice());
        assertEquals(expectedSubtotal, cartModal.getSubtotal());
        assertEquals(expectedTotal, cartModal.getTotal());

        cartModal.continueShopping();

        HeaderSection headerSection = new HeaderSection(driver);
        int cartCount = headerSection.getCartCount();
        assertEquals(3, cartCount);
    }
}
