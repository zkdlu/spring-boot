package com.zkdlu.payment.domain;

import com.zkdlu.order.domain.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "PAYMENTS")
public class Payment {

    public enum State {
        PREPARE, PAYED, COMPLETE
    }

    @Id
    private String id;
    @Enumerated(value = EnumType.STRING)
    private State state;
    @OneToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @Builder
    public Payment(String id, Order order) {
        this.id = id;
        this.order = order;
        this.state = State.PREPARE;
    }

    public void payed() {
        this.state = State.PAYED;

        order.payed();
    }
}
