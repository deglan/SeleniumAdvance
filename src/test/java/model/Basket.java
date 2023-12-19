package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Data
public class Basket {

    private Map<String, BasketLine> products;

    public Basket() {
        this.products = new HashMap<>();
    }

    public Basket(HashMap<String, BasketLine> products){
        this.products = products;
    }

    public void addProduct(Product product, int quantity) {
        BasketLine line = products.get(product.getName());
        if (line == null) {
            line = new BasketLine(product, product.getPrice().multiply(BigDecimal.valueOf(quantity)), quantity);
            products.put(product.getName(), line);
            return;
        }
        line.updateQuantity(quantity);
    }

    public void removeProduct(String productName) {
        products.remove(productName);
    }

    public BigDecimal getTotalPrice() {
        return products.values().stream()
                .map(BasketLine::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
