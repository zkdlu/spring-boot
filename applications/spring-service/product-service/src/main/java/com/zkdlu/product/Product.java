package com.zkdlu.product;

public class Product {
    private String id;
    private String name;
    private int price;
    private int stock;

    public Product(String id, int price, int stock) {
        this.id = id;
        this.name = "product-" + id;
        this.price = price;
        this.stock = stock;
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

    public int getStock() { return stock; }

    public void order(int count) {
        if (stock - count < 0) {
            throw new IllegalStateException("재고가 부족합니다.");
        }

        stock -= count;
    }
}
