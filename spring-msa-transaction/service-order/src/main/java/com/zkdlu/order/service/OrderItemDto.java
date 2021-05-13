package com.zkdlu.order.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderItemDto {
    private String id;
    private String name;
    private int price;
}
