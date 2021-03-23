package com.zkdlu.event.order.domain;

import com.zkdlu.event.event.PurchaseRequestEvent;
import com.zkdlu.event.purchase.service.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;

public class Order {
    private final Logger log = LoggerFactory.getLogger(Order.class);
    private long orderId;
    private OrderStatus orderStatus;

    public Order(long orderId) {
        this.orderId = orderId;
    }

    public void pay(ApplicationEventPublisher publisher) {
        verifyPayable();
        startedPay();

        try {
            publisher.publishEvent(new PurchaseRequestEvent(this, orderId));
            completePay();
        } catch (RuntimeException e) {
            log.info("상품 결제 중 에러가 발생");
        }
    }

    private void completePay() {
        this.orderStatus = OrderStatus.PREPARING;
    }

    private void startedPay() {
        this.orderStatus = OrderStatus.PAYMENT_WAITING;
    }

    private void verifyPayable() { }
}
