package com.zkdlu.product.domain;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private Set<Product> products = new LinkedHashSet<>();

    @PostConstruct
    public void init() {
        for (int i = 0; i < 100; i++) {
            products.add(Product.builder()
                    .id(UUID.randomUUID())
                    .name("옷" + i)
                    .image("https://placeimg.com/100/100/any")
                    .build());
            products.add(Product.builder()
                    .id(UUID.randomUUID())
                    .name("신발" + i)
                    .image("https://placeimg.com/100/100/any")
                    .build());
            products.add(Product.builder()
                    .id(UUID.randomUUID())
                    .name("장난감" + i)
                    .image("https://placeimg.com/100/100/any")
                    .build());
        }
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
