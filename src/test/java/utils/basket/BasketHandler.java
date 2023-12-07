package utils.basket;

import base.UrlProvider;
import model.Basket;
import model.BasketLine;
import model.Product;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import pageObject.basket.BasketPage;
import pageObject.home.HomePage;
import pageObject.product.ProductElementMiniature;
import pageObject.product.ProductPage;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Random;

public class BasketHandler {

    public static void addRandomProductToBasket(WebDriver driver, HomePage homePage, Basket testBasket) {
        driver.get(UrlProvider.HOME_URL.getUrl());
        ProductElementMiniature randomProduct = homePage.getProductListSection().getRandomProductElement();
        String productName = randomProduct.getTitle();
        randomProduct.selectProduct(productName);
        ProductPage productPage = new ProductPage(driver);
        BigDecimal productPrice = productPage.getProductPrice();

        Random random = new Random();
        int quantity = 1 + random.nextInt(5);

        productPage.setQuantity(quantity);
        productPage.addToCart();

        Product product = new Product(productName, productPrice, quantity);
        testBasket.addProduct(product, quantity);
    }

    public static void verifyBasketContents(Basket testBasket, BasketPage basketPage) {
        basketPage.initializeProductsMap();
        for (Map.Entry<String, BasketLine> entry : testBasket.getProducts().entrySet()) {
            String productName = entry.getKey();
            BasketLine expectedLine = entry.getValue();

            // Debug: Print the product name being checked
            System.out.println("Checking product: " + productName);

            BasketLine actualLine = basketPage.getProductDetails(productName);

            // Debug: Print actualLine to see if it's null
            System.out.println("Actual Line: " + actualLine);

            Assertions.assertThat(actualLine).as("Check if product exists in the basket: %s", productName).isNotNull();

            Assertions.assertThat(actualLine.getProduct().getPrice()).as("Check price for product: %s", productName).isEqualByComparingTo(expectedLine.getProduct().getPrice());

            Assertions.assertThat(actualLine.getQuantity()).as("Check quantity for product: %s", productName).isEqualTo(expectedLine.getQuantity());
        }
    }

    public static void verifyTotalPrice(Basket testBasket, BasketPage basketPage) {
        basketPage.initializeProductsMap();
        BigDecimal expectedProductTotalPrice = testBasket.getTotalPrice();
        String shippingCostStr = basketPage.getSubtotalShipping().replace("$", "").trim();
        BigDecimal shippingCost = new BigDecimal(shippingCostStr.isEmpty() ? "0" : shippingCostStr);
        BigDecimal expectedTotalPrice = expectedProductTotalPrice.add(shippingCost);
        BigDecimal actualTotalPrice = new BigDecimal(basketPage.getTotal().replace("$", "").trim());

        Assertions.assertThat(actualTotalPrice).as("Check if the total price (including shipping) is correctly matched").isEqualByComparingTo(expectedTotalPrice);
    }
}
