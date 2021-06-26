package com.zkdlu.event.order.domain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Order {
    private long orderId;
    private OrderStatus orderStatus;

    public Order(long orderId) {
        this.orderId = orderId;
    }

    public void place() {
        this.orderStatus = OrderStatus.PAYMENT_WAITING;
    }

    public void payed() {
        this.orderStatus = OrderStatus.PAYED;
    }
}
