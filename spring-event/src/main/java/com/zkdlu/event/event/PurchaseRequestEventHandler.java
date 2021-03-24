package com.zkdlu.event.event;

import com.zkdlu.event.purchase.service.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PurchaseRequestEventHandler {
        //implements ApplicationListener<PurchaseRequestEvent> {
    private final Logger log = LoggerFactory.getLogger(PurchaseRequestEventHandler.class);
    private final PurchaseService purchaseService;

    public PurchaseRequestEventHandler(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @EventListener
    public void onEvent(PurchaseRequestEvent event) {
        log.info(String.valueOf(event.getTimestamp()));
        purchaseService.purchase(event.getOrderId());
    }
}
