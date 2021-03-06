package ru.gb.MyMarket.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.MyMarket.market.dto.ProductDto;
import ru.gb.MyMarket.market.models.Cart;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
    private final Cart cart;

    @GetMapping("/")
    public List<ProductDto> findAll() {
        return cart.getProducts();
    }

    @PostMapping("/")
    public void deleteProductFromCart(@RequestBody ProductDto product) {
        cart.getProducts().remove(product);
    }
}
