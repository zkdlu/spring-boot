package com.zkdlu.display;

public class Product {
    public static final Product Empty = new Product("0", "[Sold out]", 0, 0);

    private String id;
    private String name;
    private int price;
    private int stock;

    public Product() {
    }

    public Product(String id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
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

    public int getStock() {
        return stock;
    }
}
