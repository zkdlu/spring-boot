package com.zkdlu.event.event;

import org.springframework.context.ApplicationEvent;

public class PurchaseRequestEvent extends ApplicationEvent {
    private long orderId;

    public PurchaseRequestEvent(Object source, long orderId) {
        super(source);
        this.orderId = orderId;
    }

    public long getOrderId() {
        return orderId;
    }
}
