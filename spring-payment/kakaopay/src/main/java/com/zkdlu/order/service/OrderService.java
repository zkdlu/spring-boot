package com.zkdlu.order.service;

import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.zkdlu.order.domain.Order;
import com.zkdlu.order.domain.OrderItem;
import com.zkdlu.order.domain.OrderRepository;
import com.zkdlu.payment.service.remote.PayReady;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional
    public PayRequest placeOrder(List<OrderItem> orderItems) {
        Order order = Order.builder()
                .id(UUID.randomUUID().toString())
                .orderItems(orderItems)
                .build();

        PayReady payReady = new PayReady("tid",
                "https://mockup-pg-web.kakao.com/v1/299542fcd10f1d3c91e472e927f23192181ac7074a2279790d5b24272b7dd34d/info",
                new Date());

        orderRepository.save(order);

        return PayRequest.builder()
                .payReady(payReady)
                .order(order)
                .build();
    }
}
