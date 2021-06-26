package com.zkdlu.tdd.service;

import com.zkdlu.tdd.domain.order.Order;
import com.zkdlu.tdd.domain.order.OrderItem;
import com.zkdlu.tdd.domain.order.OrderRepository;
import com.zkdlu.tdd.domain.shop.Shop;
import com.zkdlu.tdd.domain.shop.ShopRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ShopRepository shopRepository;

    @Transactional
    public String placeOrder(OrderDto orderDto) {
        Shop shop = shopRepository.findById(orderDto.getShopId())
                .orElseThrow(IllegalStateException::new);

        var orderItems = shop.getMenus().stream()
                .map(OrderItem::new)
                .collect(Collectors.toList());

        Order order = Order.builder()
                .id(orderDto.getId())
                .shop(shop)
                .orderItems(orderItems)
                .build();

        order.place();
        orderRepository.save(order);

        return order.getId();
    }

    @Transactional
    public String payedOrder(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(IllegalStateException::new);

        order.payed();
        return order.getId();
    }

    @Transactional
    public String acceptOrder(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(IllegalStateException::new);

        order.accept();
        return order.getId();
    }

    @Transactional
    public String deliveryOrder(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(IllegalStateException::new);

        order.delivery();
        return order.getId();
    }

    @Transactional
    public String completeOrder(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(IllegalStateException::new);

        order.complete();
        return order.getId();
    }

    @Transactional
    public String cancelOrder(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(IllegalStateException::new);

        order.cancel();
        return order.getId();
    }
}
