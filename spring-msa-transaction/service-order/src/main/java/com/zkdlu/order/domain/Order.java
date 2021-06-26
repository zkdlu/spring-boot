package com.zkdlu.order.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor
public class Order {
    private String id;
    private List<OrderLineItem> orderItems;

    @Builder
    public Order(String id, List<OrderLineItem> orderItems) {
        this.id = id;
        this.orderItems = orderItems;
    }
}
