package com.zkdlu.order.api;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class Cart {
    private List<CartItem> products = new ArrayList<>();
}
