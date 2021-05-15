package com.zkdlu.order.api;

import com.zkdlu.order.domain.OrderLineItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@NoArgsConstructor
public class CartItem {
    private String id;
    private String name;
    private int price;

    public OrderLineItem toOrderLineItem() {
        return new OrderLineItem(id, name, price);
    }
}
