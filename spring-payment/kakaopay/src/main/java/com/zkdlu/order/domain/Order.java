package com.zkdlu.order.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @OneToMany
    @JoinColumn(name = "ORDER_ID")
    private List<OrderLineItem> orderItems;
}
