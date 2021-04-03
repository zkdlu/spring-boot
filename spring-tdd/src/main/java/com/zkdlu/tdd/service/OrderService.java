package com.zkdlu.tdd.service;

import com.zkdlu.tdd.domain.Order;
import com.zkdlu.tdd.domain.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public String placeOrder(OrderDto orderDto) {
        Order order = new Order(UUID.randomUUID().toString(),
                orderDto.getItem(),
                orderDto.getPrice());

        order.place();
        orderRepository.save(order);

        return order.getId();
    }

    public String payedOrder(String orderId) {
        Order order = orderRepository.findById(orderId);

        order.payed();

        orderRepository.update(order);

        return order.getId();
    }

    public String completeOrder(String orderId) {
        Order order = orderRepository.findById(orderId);

        order.payed();

        orderRepository.update(order);

        return order.getId();
    }
}
