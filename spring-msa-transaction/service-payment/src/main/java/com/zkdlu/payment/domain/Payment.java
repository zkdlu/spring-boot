package com.zkdlu.payment.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Payment {
    public enum State {
        PREPARE, PAYED, COMPLETE
    }

    private String id;
    private String productId;
    private int price;
    private State state;

    @Builder
    public Payment(String id, String productId, int price) {
        this.id = id;
        this.productId = productId;
        this.price = price;
        this.state = State.PREPARE;
    }

    public void pay() {
        this.state = State.PAYED;
    }

    public void complete() {
        this.state = State.COMPLETE;
    }
}
