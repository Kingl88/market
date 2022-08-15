package ru.gb.MyMarket.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.gb.MyMarket.market.dto.Cart;
import ru.gb.MyMarket.market.dto.StringResponse;
import ru.gb.MyMarket.market.services.CartService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping("/{uuid}")//получение корзины из Redis
    public Cart getCart(Principal principal, @PathVariable String uuid) {
        String username = getUsername(principal);
        return cartService.getCurrentCart(getCurrentCartUuid(username, uuid));
    }

    @GetMapping("/generate")//При входе на сайт, пользователю создается случайный набор символов UUID, это будет индитифакор корзины неавторизованного пользователя в Redis.
    public StringResponse getCart() {
        return new StringResponse(cartService.generateCartUuid());//сделал класс StringResponse. только ради того что бы передавались данные в формате JSON.
    }

    @GetMapping("/{uuid}/add/{productId}")//добавление продукта в корзину по Id продукта
    public void add(Principal principal, @PathVariable String uuid, @PathVariable Long productId) {
        String username = getUsername(principal);
        cartService.addToCart(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/decrement/{productId}")//уменьшение количества продукта в корзине по Id продукта
    public void decrement(Principal principal, @PathVariable String uuid, @PathVariable Long productId) {
        String username = getUsername(principal);
        cartService.decrementItem(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/remove/{productId}")//удаление продукта в корзине по Id продукта
    public void remove(Principal principal, @PathVariable String uuid, @PathVariable Long productId) {
        String username = getUsername(principal);
        cartService.removeItemFromCart(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/clear")//очистка корзины
    public void clear(Principal principal, @PathVariable String uuid) {
        String username = getUsername(principal);
        cartService.clearCart(getCurrentCartUuid(username, uuid));
    }

    @GetMapping("/{uuid}/merge")//слияние двух корзин
    public void merge(Principal principal, @PathVariable String uuid) {
        String username = getUsername(principal);
        cartService.merge(
                getCurrentCartUuid(username, null),
                getCurrentCartUuid(null, uuid)
        );
    }

    private static String getUsername(Principal principal) {//получение username из Principal (Spring Security Context)
        String username = null;
        if (principal != null) {
            username = principal.getName();
        }
        return username;
    }

    private String getCurrentCartUuid(String username, String uuid) {//получение ключа для получения данных из Redis
        if (username != null) {
            return cartService.getCartUuidFromSuffix(username);
        }
        return cartService.getCartUuidFromSuffix(uuid);
    }
}
