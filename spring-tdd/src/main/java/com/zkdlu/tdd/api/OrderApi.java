package com.zkdlu.tdd.api;

import com.zkdlu.tdd.service.OrderDto;
import com.zkdlu.tdd.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrderApi {
    private final OrderService orderService;

    @PostMapping
    public String placeOrder(@RequestBody OrderDto orderDto) {
        return orderService.placeOrder(orderDto);
    }
}
