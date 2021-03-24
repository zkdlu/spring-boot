package com.zkdlu.demo.domain.shop;

public class Product {
    private long id;
    private String name;
    private int price;
    private int amount;

    public Product(long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = 1;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public boolean canOrder() {
        return amount > 0;
    }
}