package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public void addProduct(Product product, int quantity) {
        BasketLine line = products.get(product.getName());
        if (line == null) {
            line = new BasketLine(product, product.getPrice().multiply(BigDecimal.valueOf(quantity)), quantity);
            products.put(product.getName(), line);
        } else {
            updateProductQuantity(product.getName(), line.getQuantity() + quantity);
        }
    }

    public void updateProductQuantity(String productName, int newQuantity) {
        BasketLine line = products.get(productName);
        if (line != null) {
            line.setQuantity(newQuantity);
            line.setTotalPrice(line.getProduct().getPrice().multiply(BigDecimal.valueOf(newQuantity)));
        }
    }

    public void removeProduct(String productName) {
        products.remove(productName);
    }

    public BigDecimal getTotalPrice() {
        BigDecimal total = BigDecimal.ZERO;
        for (BasketLine line : products.values()) {
            total = total.add(line.getTotalPrice());
        }
        return total;
    }
}
