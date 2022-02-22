package com.zkdlu.order.service;

import com.zkdlu.order.domain.Order;
import com.zkdlu.order.domain.OrderLineItem;
import com.zkdlu.order.service.remote.PayReady;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final RestTemplate restTemplate;

    public PayRequest placeOrder(List<OrderLineItem> orderLineItems) {
        Order order = Order.builder()
                .id(UUID.randomUUID().toString())
                .orderItems(orderLineItems)
                .build();

        var payReady =  restTemplate.postForObject("http://localhost:8082/pay", order, PayReady.class);

        return PayRequest.builder()
                .order(order)
                .payReady(payReady)
                .build();
    }
}
