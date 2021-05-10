package com.zkdlu.payment.domain;

import com.zkdlu.payment.service.remote.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Payment {
    public enum State {
        PREPARE, PAYED, COMPLETE
    }

    private String id;
    private Product product;
    private State state;

    @Builder
    public Payment(String id, Product product) {
        this.id = id;
        this.product = product;
        this.state = State.PREPARE;
    }

    public void pay() {
        this.state = State.PAYED;
    }

    public void complete() {
        this.state = State.COMPLETE;
    }
}
