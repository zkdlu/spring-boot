package com.zkdlu.order.api;

import com.zkdlu.order.service.OrderService;
import com.zkdlu.order.service.PayRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrderApi {
    private final OrderService orderService;

    @PostMapping("/order")
    public PayRequest order(@RequestBody Cart cart) {
        return null;
//        return orderService.placeOrder(cart.getItems()
//                .stream()
//                .map(CartItem::toOrderLineItem)
//                .collect(Collectors.toList())
//        );
    }
}
