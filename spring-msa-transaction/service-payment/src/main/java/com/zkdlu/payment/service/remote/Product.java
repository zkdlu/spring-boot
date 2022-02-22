package com.zkdlu.payment.service.remote;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Getter
public class Product {
    private UUID id;
    private String name;
    private String image;
    private int price;
    private int stock;
}
