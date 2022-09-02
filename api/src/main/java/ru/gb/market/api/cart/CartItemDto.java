package ru.gb.market.api.cart;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель продукта в корзине")
public class CartItemDto {
    @Schema(description = "Id продукта", required = true)
    private Long productId;
    @Schema(description = "Название продукта", required = true)
    private String productTitle;
    @Schema(description = "Количество", required = true)
    private int count;
    @Schema(description = "Стоимость за единицу", required = true)
    private int pricePerProduct;
    @Schema(description = "Общая стоимость", required = true)
    private int price;

    public CartItemDto(Long productId, String productTitle, int count, int pricePerProduct, int price) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.count = count;
        this.pricePerProduct = pricePerProduct;
        this.price = price;
    }

    public CartItemDto() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPricePerProduct() {
        return pricePerProduct;
    }

    public void setPricePerProduct(int pricePerProduct) {
        this.pricePerProduct = pricePerProduct;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
