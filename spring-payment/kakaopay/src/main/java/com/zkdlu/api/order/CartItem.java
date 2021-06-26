package com.zkdlu.api.order;

import com.zkdlu.domain.order.OrderItem;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
class CartItem {
    private Long id;
    private String name;
    private int price;

    OrderItem toOrderItem() {
        return new OrderItem(id, name, price);
    }
}
