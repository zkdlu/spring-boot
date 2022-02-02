package com.zkdlu.product.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    List<Product> findAll(int page);
    List<Product> findAll();
    Optional<Product> findById(UUID uuid);
    Product save(Product product);
}
