package com.zkdlu.demo.domain.order;

import com.zkdlu.demo.domain.shop.Product;

import java.time.LocalDateTime;

public class Order {
    private long id;
    private OrderState orderState;
    private LocalDateTime orderedAt;
    private Product orderItem;

    private Order() {
        id = 1L;
        orderedAt = LocalDateTime.now();
        orderItem = new Product(2L, "컴퓨터", 10000);
    }

    public static Order createOrder() {
        return new Order();
    }

    public void place() {
        validate();
        ordered();
    }

    private void ordered() {
        orderState = OrderState.PREPARE;
    }

    private void validate() {
        if (!orderItem.canOrder()) {
            throw new IllegalStateException("재고가 없습니다.");
        }
    }
}
