package tests.shoppingCart;

import driver.DriverSetUp;
import org.junit.jupiter.api.BeforeEach;
import pageObject.category.ProductCategoryPage;
import pageObject.home.sections.HeaderSection;
import pageObject.product.CartModal;
import pageObject.product.ProductPage;

public class ShoppingCartSetUp extends DriverSetUp {

    ProductCategoryPage categoryPage;
    ProductPage productPage;
    CartModal cartModal;
    HeaderSection headerSection;


    @BeforeEach
    public void setUpShoppingCart() {
        categoryPage = new ProductCategoryPage(driver);
        productPage = new ProductPage(driver);
        cartModal = new CartModal(driver);
        headerSection = new HeaderSection(driver);
    }
}
