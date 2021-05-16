package com.zkdlu.payment.domain;

import com.zkdlu.payment.service.remote.Order;
import com.zkdlu.payment.service.remote.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Payment {
    public enum State {
        PREPARE, PAYED, COMPLETE
    }

    private String id;
    private Order order;
    private State state;

    @Builder
    public Payment(String id, Order order) {
        this.id = id;
        this.order = order;
        this.state = State.PREPARE;
    }

    public void pay() {
        this.state = State.PAYED;
    }

    public void complete() {
        this.state = State.COMPLETE;
    }
}
