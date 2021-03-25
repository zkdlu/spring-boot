package com.zkdlu.demo.service.order;

import lombok.Getter;

@Getter
public class OrderRequest {
    private long orderId;
    private long productId;

    public OrderRequest(long orderId, long productId) {
        this.orderId = orderId;
        this.productId = productId;
    }
}
