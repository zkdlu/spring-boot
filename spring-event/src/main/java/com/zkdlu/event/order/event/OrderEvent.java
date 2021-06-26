package com.zkdlu.event.order.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrderEvent extends ApplicationEvent {
    private long orderId;

    public OrderEvent(Object source, long orderId) {
        super(source);

        this.orderId = orderId;
    }
}
