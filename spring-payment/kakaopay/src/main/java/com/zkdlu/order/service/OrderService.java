package com.zkdlu.order.service;

import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.zkdlu.order.domain.Order;
import com.zkdlu.order.domain.OrderItem;
import com.zkdlu.order.domain.OrderRepository;
import com.zkdlu.payment.service.KakaoPay;
import com.zkdlu.payment.service.remote.PayReady;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final KakaoPay kakaoPay;

    @Transactional
    public PayRequest placeOrder(List<OrderItem> orderItems) {
        log.info("order service: {}", Thread.currentThread().getId());

        Order order = Order.builder()
                .id(UUID.randomUUID().toString())
                .orderItems(orderItems)
                .build();

        orderRepository.save(order);

        PayReady payReady = kakaoPay.prepare(order);
                //restTemplate.postForObject("http://localhost:8080/pay", order, PayReady.class);


        return PayRequest.builder()
                .payReady(payReady)
                .order(order)
                .build();
    }
}
