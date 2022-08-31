package ru.gb.market.cart.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gb.market.api.core.ProductDto;

@Setter
@Getter
@NoArgsConstructor
public class CartItem {
    private Long productId;
    private String productTitle;
    private int count;
    private int pricePerProduct;
    private int price;

    public CartItem(ProductDto productDto) {
        this.productId = productDto.getId();
        this.productTitle = productDto.getTitle();
        this.count = 1;
        this.pricePerProduct = productDto.getPrice();
        this.price = productDto.getPrice();
    }
    public void changeCount(int delta){//метод для изменения количества продукта
        this.count+=delta;
        this.price = this.count * this.pricePerProduct;
    }
}
