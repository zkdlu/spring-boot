package com.zkdlu.demo.domain.order;

public enum OrderState {
    PREPARE("결제 대기"),
    DELIVERING ("배송 중"),
    COMPLETE("주문 완료"),
    CANCELED("취소");

    private String description;

    OrderState(String description) {
        this.description = description;
    }
}
