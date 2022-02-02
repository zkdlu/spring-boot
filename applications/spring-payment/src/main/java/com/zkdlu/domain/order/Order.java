package com.zkdlu.domain.order;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "ORDERS")
public class Order {
    public enum State {
        PREPARE, PAYED, COMPLETE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
    private List<OrderItem> orderItems;
    @Enumerated(value = EnumType.STRING)
    private State state;

    @Builder
    public Order(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
        this.state = State.PREPARE;
    }

    public void payed() {
        this.state = State.PAYED;
    }

    private void verify() {
        for (OrderItem orderItem : orderItems) {
            orderItem.verify();
        }
    }
}

