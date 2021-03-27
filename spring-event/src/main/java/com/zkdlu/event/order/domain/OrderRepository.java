package com.zkdlu.event.order.domain;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OrderRepository {

    public Optional<Order> findById(long orderId) {
        return Optional.of(new Order(orderId));
    }

    public Order save(Order order) {
        return order;
    }
}
