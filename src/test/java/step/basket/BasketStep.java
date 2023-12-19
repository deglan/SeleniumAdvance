package step.basket;

import base.UrlProvider;
import model.Basket;
import model.BasketLine;
import model.Product;
import model.converter.BasketModelConverter;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObject.base.BasePage;
import pageObject.basket.BasketLineSection;
import pageObject.basket.BasketPage;
import pageObject.home.HomePage;
import pageObject.product.ProductElementMiniature;
import pageObject.product.ProductPage;
import step.base.StepBase;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BasketStep extends StepBase {

    private HomePage homePage;
    private BasketPage actualBasket;
    private Map<String, BasketLine> productsMap;

    public BasketStep(WebDriver driver, WebElement element) {
        super(driver, element);
        this.productsMap = new HashMap<>();
    }

    public BasketStep(WebDriver driver) {
        super(driver);
        this.productsMap = new HashMap<>();
    }

    public void initializeProductsMap(BasketModelConverter converter) {
        actualBasket = new BasketPage(driver);
        Basket basket = converter.toBasketModel(); // Using the converter to get Basket model
        productsMap.clear();
        for (Map.Entry<String, BasketLine> entry : basket.getProducts().entrySet()) {
            productsMap.put(entry.getKey(), entry.getValue());
        }
    }

    public void removeProduct(String productName) {
        if (actualBasket == null) {
            actualBasket = new BasketPage(driver);
        }
        for (WebElement item : actualBasket.getCartItems()) {
            BasketLineSection lineSection = new BasketLineSection(driver, item);
            if (lineSection.getProductName().equals(productName)) {
                lineSection.removeProduct();
                wait.until(ExpectedConditions.stalenessOf(item));
                break;
            }
        }
        productsMap.remove(productName);
    }

    public BasketLine getProductDetails(String productName) {
        return productsMap.get(productName);
    }

    public void addRandomProductToBasket(Basket expectedBasket) {
        homePage = new HomePage(driver);
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
        expectedBasket.addProduct(product, quantity);
    }

    public void verifyBasketContents(Basket expectedBasket) {
        BasketModelConverter converter = new BasketPage(driver);
        initializeProductsMap(converter);
        for (Map.Entry<String, BasketLine> entry : expectedBasket.getProducts().entrySet()) {
            String productName = entry.getKey();
            BasketLine expectedLine = entry.getValue();

            BasketLine actualLine = getProductDetails(productName);

            Assertions.assertThat(actualLine).as("Check if product exists in the basket: %s", productName).isNotNull();

            Assertions.assertThat(actualLine.getProduct().getPrice()).as("Check price for product: %s", productName).isEqualByComparingTo(expectedLine.getProduct().getPrice());

            Assertions.assertThat(actualLine.getQuantity()).as("Check quantity for product: %s", productName).isEqualTo(expectedLine.getQuantity());
        }
    }

    public void verifyTotalPrice(Basket expectedBasket) {
        BasketPage basketPage = new BasketPage(driver);
        BasketModelConverter converter = basketPage;
        initializeProductsMap(converter);

        BigDecimal expectedProductTotalPrice = expectedBasket.getTotalPrice();
        String shippingCostStr = basketPage.getSubtotalShipping().replace("$", "").trim();
        BigDecimal shippingCost = new BigDecimal(shippingCostStr.isEmpty() ? "0" : shippingCostStr);
        BigDecimal expectedTotalPrice = expectedProductTotalPrice.add(shippingCost);
        BigDecimal actualTotalPrice = new BigDecimal(basketPage.getTotal().replace("$", "").trim());

        Assertions.assertThat(actualTotalPrice)
                .as("Check if the total price (including shipping) is correctly matched")
                .isEqualByComparingTo(expectedTotalPrice);
    }
}
