package com.zkdlu.event.order.service;

import lombok.Getter;

@Getter
public class OrderRequest {
    private long orderId;

    public OrderRequest(long orderId) {
        this.orderId = orderId;
    }
}
