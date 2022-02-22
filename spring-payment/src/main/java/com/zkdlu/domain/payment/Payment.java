package com.zkdlu.domain.payment;

import com.zkdlu.domain.order.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "PAYMENTS")
public class Payment {

    public enum State {
        PREPARE, PAYED, COMPLETE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private State state;
    @OneToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @Builder
    public Payment(Long id, Order order) {
        this.id = id;
        this.order = order;
        this.state = State.PREPARE;
    }

    public void payed() {
        this.state = State.PAYED;

        order.payed();
    }
}
