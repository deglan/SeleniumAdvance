package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BasketLine {

    private Product product;
    private BigDecimal totalPrice;
    private int quantity;

    public void updateQuantity(int quantity) {
        int newQuantity = quantity + product.getQuantity();
        product.setQuantity(newQuantity);
        this.setQuantity(newQuantity);
        this.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(newQuantity)));
    }
}
