package com.zkdlu.tdd.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {
    private List<Order> orders = new ArrayList<>();
    public Order save(Order order) {
        orders.add(order);
        return order;
    }

    public Order findById(String id) {
        for (Order order : orders) {
            if (order.getId().equals(id)) {
                return order;
            }
        }

        return null;
    }

    public void update(Order newOrder) {
        orders.remove(findById(newOrder.getId()));
        orders.add(newOrder);
    }
}
