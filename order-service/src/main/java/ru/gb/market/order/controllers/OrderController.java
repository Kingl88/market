package ru.gb.market.order.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.api.order.OrderDetailsDto;
import ru.gb.market.api.order.OrderDto;
import ru.gb.market.api.order.OrderItemDto;
import ru.gb.market.order.mapper.OrderMapper;
import ru.gb.market.order.services.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final OrderService orderService;
    private final OrderMapper mapper;

    @PostMapping("/createOrder")
    public void save(@RequestHeader(required = false) String username, @RequestBody OrderDetailsDto orderDetailsDto) {
        OrderDto list= mapper.mapToDto(orderService.save(username, orderDetailsDto));
        kafkaTemplate.send("market_topic",list );
        System.out.println();
        ;
    }

    @GetMapping("/findAllOrdersByUsername")
    public List<OrderDto> findByUsername(@RequestHeader(required = false) String username) {
        return orderService.findAllByUsername(username).stream().map(mapper::mapToDto).toList();
    }
}
