package com.zkdlu.hexagonal.orderapp.domain.order;

import lombok.Getter;

@Getter
public class Order {
    public enum OrderState { PREPARE, COMPLETE }

    private String id;
    private OrderState orderState;
    private int money;

    public Order(String id, int money) {
        this.id = id;
        this.orderState = OrderState.PREPARE;
        this.money = money;
    }

    public void place() {
        this.orderState = OrderState.COMPLETE;
    }
}
