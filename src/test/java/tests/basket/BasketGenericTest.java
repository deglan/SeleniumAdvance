package tests.basket;

import base.UrlProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pageObject.basket.BasketPage;
import step.basket.BasketStep;

public class BasketGenericTest extends BasketSetUp {

    @Test
    public void shouldCheckBasket() {
        driver.get(UrlProvider.HOME_URL.getUrl());

        for (int i = 0; i < 10; i++) {
            with(BasketStep.class)
                    .addRandomProductToBasket(expectedBasket);
        }
        driver.get(UrlProvider.BASKET_URL.getUrl());
        with(BasketStep.class).verifyBasketContents(expectedBasket);
        with(BasketStep.class).verifyTotalPrice(expectedBasket);

    }

    @Test
    public void shouldRemoveProductsFromBasket() {

        with(BasketStep.class)
                .addRandomProductToBasket(expectedBasket);
        with(BasketStep.class)
                .addRandomProductToBasket(expectedBasket);

        driver.get(UrlProvider.BASKET_URL.getUrl());

        with(BasketStep.class)
                .verifyTotalPrice(expectedBasket);

        with(BasketStep.class).removeProduct(expectedBasket.getProducts().keySet().iterator().next());
        expectedBasket.removeProduct(expectedBasket.getProducts().keySet().iterator().next());

        with(BasketStep.class)
                .verifyTotalPrice(expectedBasket);

        with(BasketStep.class).removeProduct(expectedBasket.getProducts().keySet().iterator().next());
        expectedBasket.removeProduct(expectedBasket.getProducts().keySet().iterator().next());

        Assertions.assertThat(at(BasketPage.class)
                .isEmptyBasketLabelDisplayed()).as("Check if empty basket label is displayed").isTrue();
    }

}
