package com.zkdlu.tdd.service;

import com.zkdlu.tdd.domain.order.OrderRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
}
