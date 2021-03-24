package com.zkdlu.demo.repository;

import com.zkdlu.demo.domain.shop.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
    public Product findById(long id) {
        return new Product(id, "제품" + id, 10000);
    }
}
