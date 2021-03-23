package com.zkdlu.event.order.service;

import com.zkdlu.event.order.domain.Order;
import com.zkdlu.event.order.domain.OrderRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher publisher;

    public OrderService(OrderRepository orderRepository, ApplicationEventPublisher publisher) {
        this.orderRepository = orderRepository;
        this.publisher = publisher;
    }

    public void pay(long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(RuntimeException::new);

        order.pay(publisher);
    }
}
