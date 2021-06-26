package com.zkdlu.domain.order;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class OrderItem {
    @Id
    private Long id;
    private String name;
    private int price;

    @Builder
    public OrderItem(Long id, String name, int price) {
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

