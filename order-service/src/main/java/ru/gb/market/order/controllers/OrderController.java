package ru.gb.market.order.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.api.order.OrderDetailsDto;
import ru.gb.market.api.order.OrderDto;
import ru.gb.market.order.mapper.OrderMapper;
import ru.gb.market.order.services.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper mapper;

    @PostMapping("/createOrder")
    public void save(@RequestHeader(required = false) String username, @RequestBody OrderDetailsDto orderDetailsDto) {
        orderService.save(username, orderDetailsDto);
    }

    @GetMapping("/findAllOrdersByUsername")
    public List<OrderDto> findByUsername(@RequestHeader(required = false) String username) {
        return orderService.findAllByUsername(username).stream().map(mapper::mapToDto).toList();
    }
}
