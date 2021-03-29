package com.zkdlu.event.order.service;

import com.zkdlu.event.infra.sms.SmsClient;
import com.zkdlu.event.order.domain.Order;
import com.zkdlu.event.order.domain.OrderRepository;
import com.zkdlu.event.order.event.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher eventPublisher;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order(orderRequest.getOrderId());
        order.place();
        orderRepository.save(order);

        log.info(Thread.currentThread().toString());
        eventPublisher.publishEvent(new OrderEvent(this, orderRequest.getOrderId()));
    }
}
