package com.zkdlu.api.order;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
class Cart {
    private List<CartItem> items = new ArrayList<>();
}