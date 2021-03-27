package com.zkdlu.hexagonal.orderapp.application.order.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderResult {
    private long orderId;
    private int money;
}
