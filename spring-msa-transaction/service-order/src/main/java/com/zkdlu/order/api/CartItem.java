package com.zkdlu.order.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@NoArgsConstructor
public class CartItem {
    private String id;
    private String name;
    private int price;
}
