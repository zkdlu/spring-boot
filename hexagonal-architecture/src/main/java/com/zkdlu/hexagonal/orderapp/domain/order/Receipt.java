package com.zkdlu.hexagonal.orderapp.domain.order;

import lombok.Getter;

@Getter
public class Receipt {
    private long orderId;
    private int money;

    public Receipt(long orderId, int money) {
        this.orderId = orderId;
        this.money = money;
    }
}
