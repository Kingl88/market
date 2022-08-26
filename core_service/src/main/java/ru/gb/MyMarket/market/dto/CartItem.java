package ru.gb.MyMarket.market.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gb.MyMarket.market.models.Product;

@Setter
@Getter
@NoArgsConstructor
public class CartItem {
    private Long productId;
    private String productTitle;
    private int count;
    private int pricePerProduct;
    private int price;

    public CartItem(Product product) {
        this.productId = product.getId();
        this.productTitle = product.getTitle();
        this.count = 1;
        this.pricePerProduct = product.getPrice();
        this.price = product.getPrice();
    }
    public void changeCount(int delta){//метод для изменения количества продукта
        this.count+=delta;
        this.price = this.count * this.pricePerProduct;
    }
}
