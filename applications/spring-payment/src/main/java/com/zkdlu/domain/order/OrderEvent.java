package com.zkdlu.domain.order;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrderEvent extends ApplicationEvent {
    private Long orderId;
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public OrderEvent(Object source, Long orderId) {
        super(source);

        this.orderId = orderId;
    }
}
