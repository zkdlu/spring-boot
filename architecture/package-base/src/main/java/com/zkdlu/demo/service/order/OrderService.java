package com.zkdlu.demo.service.order;

import com.zkdlu.demo.domain.order.Order;
import com.zkdlu.demo.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    @Transactional
    public void placeOrder(Order order) {
        order.place();
        orderRepository.save(order);
    }
}
