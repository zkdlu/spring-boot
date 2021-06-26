package com.zkdlu.hexagonal.orderapp.domain.order;

import lombok.Getter;

@Getter
public class Receipt {
    private String orderId;
    private int money;

    public Receipt(String orderId, int money) {
        this.orderId = orderId;
        this.money = money;
    }
}
