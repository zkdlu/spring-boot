package com.zkdlu.product.service;

import com.zkdlu.product.domain.Product;
import com.zkdlu.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByPage(int page) {
        return productRepository.findAll(page);
    }

    public Product getProduct(String productId) {
        return productRepository.findById(UUID.fromString(productId))
                .orElseThrow(IllegalStateException::new);
    }
}
