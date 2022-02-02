package com.zkdlu.product;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private final List<Product> products = Arrays.asList(new Product("1", 1000, 10),
            new Product("2", 1500, 10),
            new Product("3", 2000, 10));

    public List<Product> findAll() {
        return products;
    }

    public Optional<Product> findById(String productId) {
        return products.stream().filter(i -> i.getId().equals(productId)).findFirst();
    }
}
