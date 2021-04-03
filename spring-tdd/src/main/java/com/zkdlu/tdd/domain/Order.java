package com.zkdlu.tdd.domain;

import lombok.Getter;

@Getter
public class Order {
    enum OrderState { NONE, PREPARE, PAYED, COMPLETE }

    private String id;
    private String item;
    private int price;
    private OrderState state;

    public Order(String id, String item, int price) {
        this.id = id;
        this.item = item;
        this.price = price;
        state = OrderState.NONE;
    }

    public void place() {
        state = OrderState.PREPARE;
        verify();
    }

    private void verify() {
        if (item == null || item.equals("")) {
            throw new IllegalStateException();
        }

        if (price < 0) {
            throw new IllegalStateException();
        }
    }

    public void payed() {
        state = OrderState.PAYED;
    }

    public void complete() {
        state = OrderState.COMPLETE;
    }
}
