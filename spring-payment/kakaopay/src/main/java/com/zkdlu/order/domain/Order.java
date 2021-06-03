package com.zkdlu.order.domain;

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
    private String id;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
    private List<OrderItem> orderItems;
    @Enumerated(value = EnumType.STRING)
    private State state;

    @Builder
    public Order(String id, List<OrderItem> orderItems) {
        this.id = id;
        this.orderItems = orderItems;
        this.state = State.PREPARE;
    }
}
