package com.zkdlu.order.api;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartItem {
    private String id;
    private String name;
    private int price;
}
