package com.zkdlu.product.domain;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private Set<Product> products = new HashSet<>();

    @PostConstruct
    public void init() {
        products.add(Product.builder()
                .id(UUID.randomUUID())
                .name("옷")
                .image("https://placeimg.com/100/100/any")
                .build());
        products.add(Product.builder()
                .id(UUID.randomUUID())
                .name("신발")
                .image("https://placeimg.com/100/100/any")
                .build());
        products.add(Product.builder()
                .id(UUID.randomUUID())
                .name("장난감")
                .image("https://placeimg.com/100/100/any")
                .build());
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    @Override
    public Optional<Product> findById(UUID uuid) {
        return products.stream()
                .filter(p -> p.getId().equals(uuid))
                .findFirst();
    }

    @Override
    public Product save(Product product) {
        products.add(product);

        return product;
    }
}
