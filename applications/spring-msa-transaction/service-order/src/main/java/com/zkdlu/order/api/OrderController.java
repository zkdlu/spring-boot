package com.zkdlu.order.api;

import com.zkdlu.order.service.OrderService;
import com.zkdlu.order.service.PayRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@Slf4j
@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order")
    public PayRequest order(@RequestBody Cart cart) {
        return orderService.placeOrder(cart.getItems()
                .stream()
                .map(CartItem::toOrderLineItem)
                .collect(Collectors.toList())
        );
    }
}
