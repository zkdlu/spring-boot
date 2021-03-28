package com.zkdlu.hexagonal.orderapp.adapter.in.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class PhoneOrderResult {
    private String orderId;
    private int price;
}
