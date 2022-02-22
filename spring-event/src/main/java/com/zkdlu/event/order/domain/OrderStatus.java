package com.zkdlu.event.order.domain;

public enum OrderStatus {
    PAYMENT_WAITING("걸제 대기중"),
    PAYED("결제 완료");

    private String description;

    OrderStatus(String description) { this.description = description; }

    public String getDescription() {
        return description;
    }
}
