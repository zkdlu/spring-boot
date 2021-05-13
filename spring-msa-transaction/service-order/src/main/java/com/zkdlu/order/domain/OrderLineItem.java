package com.zkdlu.order.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderLineItem {
    private String id;
    private String name;
    private int price;
}
