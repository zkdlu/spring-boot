package com.zkdlu.order.api;

import com.zkdlu.order.domain.OrderItem;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartItem {
    private String id;
    private String name;
    private int price;

    public OrderItem toOrderItem() {
        return new OrderItem(id, name, price);
    }
}
