package com.zkdlu.demo.domain.order;

public enum OrderState {
    PREPARE("준비 중"),
    PAYED("결제 완료"),
    DELIVERING ("배송 중"),
    COMPLETE("주문 완료"),
    CANCELED("취소");

    private String description;

    OrderState(String description) {
        this.description = description;
    }
}
