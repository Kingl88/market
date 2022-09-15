package ru.gb.market.api.order;

import java.util.List;

public class OrderDto {
    private String username;
    private Integer totalPrice;
    private List<OrderItemDto> items;
    private String address;
    private String phone;

    public OrderDto() {
    }

    public OrderDto(String username, Integer totalPrice, List<OrderItemDto> items, String address, String phone) {
        this.username = username;
        this.totalPrice = totalPrice;
        this.items = items;
        this.address = address;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }
}
