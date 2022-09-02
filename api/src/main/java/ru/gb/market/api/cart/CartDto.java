package ru.gb.market.api.cart;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
@Schema(description = "Модель корзины")
public class CartDto {
    @Schema(description = "Список продуктов в корзине", required = true)
    private  List<CartItemDto> items;
    @Schema(description = "Общая стоимость продуктов в корзине", required = true)
    private int totalPrice;

    public CartDto(List<CartItemDto> items, int totalPrice) {
        this.items = items;
        this.totalPrice = totalPrice;
    }

    public List<CartItemDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemDto> items) {
        this.items = items;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public CartDto() {

    }
}
