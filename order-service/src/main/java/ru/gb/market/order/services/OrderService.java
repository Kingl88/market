package ru.gb.market.order.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.market.api.cart.CartDto;
import ru.gb.market.api.order.OrderDetailsDto;
import ru.gb.market.order.entities.Order;
import ru.gb.market.order.entities.OrderItem;
import ru.gb.market.order.integrations.CartServiceIntegration;
import ru.gb.market.order.repositories.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartServiceIntegration integration;

    @Transactional
    public Order save(String username, OrderDetailsDto detailsDto) {
        CartDto currentCart = integration.getUserCart(username);
        Order order = new Order();
        order.setAddress(detailsDto.getAddress());
        order.setPhone(detailsDto.getPhone());
        order.setUsername(username);
        order.setTotalPrice(currentCart.getTotalPrice());
        List<OrderItem> itemList = currentCart.getItems().stream().map(o->
        {
            OrderItem item = new OrderItem();
            item.setProductId(o.getProductId());
            item.setOrder(order);
            item.setCount(o.getCount());
            item.setPricePerProduct(o.getPricePerProduct());
            item.setPrice(o.getPrice());
            return item;
        }).toList();
        order.setItems(itemList);
      return   orderRepository.save(order);
    }

    public List<Order> findAllByUsername(String username){
        return orderRepository.findAllByUsername(username);
    }
}