package com.zkdlu.product;

public class Product {
    private String id;
    private String name;
    private int price;

    public Product(String id, int price) {
        this.id = id;
        this.name = "product-" + id;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
