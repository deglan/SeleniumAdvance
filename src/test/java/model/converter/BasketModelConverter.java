package model.converter;

import model.Basket;
import model.BasketLine;

public interface BasketModelConverter {

    Basket toBasketModel();
    BasketLine toBasketLine();
}
