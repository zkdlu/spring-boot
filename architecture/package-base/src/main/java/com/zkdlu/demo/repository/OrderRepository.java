package com.zkdlu.demo.repository;

import com.zkdlu.demo.domain.order.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {
    public Order save(Order order) {
        // 저장
        return order;
    }

    public Order findById(long id) {
        return Order.createOrder();
    }
}
