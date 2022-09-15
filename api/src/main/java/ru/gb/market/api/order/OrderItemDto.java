package ru.gb.market.api.order;

public class OrderItemDto {
    private Long productId;
    private String productTitle;
    private int count;
    private int pricePerProduct;
    private int price;

    public OrderItemDto() {
    }

    public OrderItemDto(Long productId, String productTitle, int count, int pricePerProduct, int price) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.count = count;
        this.pricePerProduct = pricePerProduct;
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }
}
