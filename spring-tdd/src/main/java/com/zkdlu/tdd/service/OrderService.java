package com.zkdlu.tdd.service;

import com.zkdlu.tdd.domain.order.Order;
import com.zkdlu.tdd.domain.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
}
