package com.zkdlu.event.order.service;

import com.zkdlu.event.infra.sms.SmsClient;
import com.zkdlu.event.order.event.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderEventHandler {
    private final SmsClient smsClient;

    @EventListener
    public void send(OrderEvent orderEvent) throws InterruptedException {
        log.info("메시지를 보냅니다. 1 {}", Thread.currentThread().toString());
    }
}

@Slf4j
@Component
class OrderEventHandler2 {
    @EventListener
    public void foo(OrderEvent orderEvent) {
        log.info("메시지를 보냅니다. 2 {}", Thread.currentThread().toString());
    }
}