package com.zkdlu.product.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Getter
public class Product {
    private UUID id;
    private String name;
    private String image;
    private int stock;

    @Builder
    public Product(UUID id, String name, String image, int stock) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.stock = stock;
    }
}
