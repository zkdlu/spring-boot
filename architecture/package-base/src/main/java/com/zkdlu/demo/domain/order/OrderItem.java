package com.zkdlu.demo.domain.order;

import com.zkdlu.demo.domain.shop.Product;
import lombok.Getter;

@Getter
public class OrderItem {
    private Product product;

    public OrderItem(Product product) {
        this.product = product;
    }
}
