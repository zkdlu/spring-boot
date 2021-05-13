package com.zkdlu.order.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class Order {
    private String id;
    private List<OrderLineItem> orderItems;


}
