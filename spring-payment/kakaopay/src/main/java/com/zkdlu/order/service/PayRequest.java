package com.zkdlu.order.service;

import com.zkdlu.domain.order.Order;
import com.zkdlu.payment.service.remote.PayReady;
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
