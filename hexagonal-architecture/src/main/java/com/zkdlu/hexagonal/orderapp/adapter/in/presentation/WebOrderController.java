package com.zkdlu.hexagonal.orderapp.adapter.in.presentation;

import com.zkdlu.hexagonal.orderapp.application.order.port.in.OrderDetail;
import com.zkdlu.hexagonal.orderapp.application.order.port.in.OrderResult;
import com.zkdlu.hexagonal.orderapp.application.order.port.in.PlaceOrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class WebOrderController {
    private final PlaceOrderUseCase placeOrderUseCase;

    @GetMapping("/web")
    public OrderResult order() {

        OrderDetail orderDetail = new OrderDetail(2L, 10000);
        return placeOrderUseCase.placeOrder(orderDetail);
    }
}
