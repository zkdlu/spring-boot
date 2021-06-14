package com.zkdlu.order.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private int price;

    @Builder
    public OrderItem(String id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    private boolean remainStock;

    public void canBuy(int stock) {
        remainStock = stock > 0;
    }

    public void verify() {
        if (!remainStock) {
            throw new IllegalStateException("재고 없음");
        }
    }
}
