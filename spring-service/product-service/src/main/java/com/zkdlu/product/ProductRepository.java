package com.zkdlu.product;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class ProductRepository {
    public List<Product> findAll() {
        return Arrays.asList(new Product("1", 1000),
                new Product("2", 1500),
                new Product("3", 2000));
    }
}
