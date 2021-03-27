package com.zkdlu.event.order.service;

import com.zkdlu.event.infra.sms.SmsClient;
import com.zkdlu.event.order.event.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderEventHandler {
    private final SmsClient smsClient;

    @EventListener
    public void send(OrderEvent orderEvent) {
        smsClient.send(orderEvent.getOrderId() + "번 주문");
    }
}
