package com.zkdlu.hexagonal.orderapp.adapter.in.presentation;

import com.zkdlu.hexagonal.orderapp.application.order.port.in.OrderDetail;
import com.zkdlu.hexagonal.orderapp.application.order.port.in.OrderResult;
import com.zkdlu.hexagonal.orderapp.application.order.port.in.PlaceOrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PhoneOrderController {
    private final PlaceOrderUseCase placeOrderUseCase;

    @GetMapping("/phone")
    public OrderResult order() {

        OrderDetail orderDetail = new OrderDetail(1L, 1000);
        return placeOrderUseCase.placeOrder(orderDetail);
    }
}
