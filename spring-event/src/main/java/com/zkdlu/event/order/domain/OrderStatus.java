package com.zkdlu.event.order.domain;

public enum OrderStatus {
    PAYMENT_WAITING("걸제 대기중"),
    PREPARING("상품 준비중"),
    DELIVERING("배송 중"),
    DELIVERY_COMPLETED("배송 완료"),
    CANCELED("상품 취소");

    private String description;

    OrderStatus(String description) { this.description = description; }

    public String getDescription() {
        return description;
    }
}
