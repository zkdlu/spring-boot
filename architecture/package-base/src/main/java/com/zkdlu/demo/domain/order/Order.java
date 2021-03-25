package com.zkdlu.demo.domain.order;

import com.zkdlu.demo.domain.shop.Product;
import lombok.Getter;
import org.hibernate.type.OrderedSetType;

import java.time.LocalDateTime;

@Getter
public class Order {
    private long id;
    private OrderState orderState;
    private LocalDateTime orderedAt;
    private OrderItem orderItem;

    public Order(long id, OrderItem orderItem) {
        this.id = id;
        this.orderItem = orderItem;
        orderedAt = LocalDateTime.now();
    }

    public void place() {
        validate();
        ordered();
    }

    private void ordered() {
        orderState = OrderState.PREPARE;
    }

    private void validate() {
        if (!orderItem.getProduct().canOrder()) {
            throw new IllegalStateException("재고가 없습니다.");
        }
    }

    public void payed() {
        orderState = OrderState.DELIVERING;
    }

    public void delivery() {
        orderState = OrderState.COMPLETE;
    }
}
