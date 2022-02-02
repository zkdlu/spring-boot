package com.zkdlu.domain.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderEventHandler {

    @EventListener
    public void listen(OrderEvent orderEvent) {
        log.info("{}", orderEvent.getOrderId());
    }
}
