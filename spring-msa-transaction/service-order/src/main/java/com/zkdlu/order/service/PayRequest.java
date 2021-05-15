package com.zkdlu.order.service;

import com.zkdlu.order.domain.Order;
import com.zkdlu.order.service.remote.PayReady;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PayRequest {
    private Order order;
    private PayReady payReady;

    @Builder
    public PayRequest(Order order, PayReady payReady) {
        this.order = order;
        this.payReady = payReady;
    }
}
