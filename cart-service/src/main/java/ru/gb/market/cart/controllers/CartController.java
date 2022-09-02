package ru.gb.market.cart.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.api.cart.CartDto;
import ru.gb.market.api.dto.StringResponse;
import ru.gb.market.cart.entities.Cart;
import ru.gb.market.cart.mapper.CartMapper;
import ru.gb.market.cart.services.CartService;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;
    private final CartMapper mapper;

    @GetMapping("/{uuid}")//получение корзины из Redis
    public CartDto getCart(@RequestHeader(required = false) String username, @PathVariable String uuid) {//@RequestHeader(required = false) - достаем "username" из header
        CartDto cartDto = mapper.mapToDto(cartService.getCurrentCart(getCurrentCartUuid(username, uuid)));
        return cartDto;
    }

    @GetMapping("/generate")//При входе на сайт, пользователю создается случайный набор символов UUID, это будет индитифакор корзины неавторизованного пользователя в Redis.
    public StringResponse getCart() {
        return new StringResponse(cartService.generateCartUuid());//сделал класс StringResponse. только ради того что бы передавались данные в формате JSON.
    }

    @GetMapping("/{uuid}/add/{productId}")//добавление продукта в корзину по Id продукта
    public void add(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long productId) {
        cartService.addToCart(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/decrement/{productId}")//уменьшение количества продукта в корзине по Id продукта
    public void decrement(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long productId) {
        cartService.decrementItem(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/remove/{productId}")//удаление продукта в корзине по Id продукта
    public void remove(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long productId) {
        cartService.removeItemFromCart(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/clear")//очистка корзины
    public void clear(@RequestHeader(required = false) String username, @PathVariable String uuid) {
        cartService.clearCart(getCurrentCartUuid(username, uuid));
    }

    @GetMapping("/{uuid}/merge")//слияние двух корзин
    public void merge(@RequestHeader String username, @PathVariable String uuid) {
        cartService.merge(
                getCurrentCartUuid(username, null),
                getCurrentCartUuid(null, uuid)
        );
    }
    private String getCurrentCartUuid(String username, String uuid) {//получение ключа для получения данных из Redis
        if (username != null) {
            return cartService.getCartUuidFromSuffix(username);
        }
        return cartService.getCartUuidFromSuffix(uuid);
    }
}
