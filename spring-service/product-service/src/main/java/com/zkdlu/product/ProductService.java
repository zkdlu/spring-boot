package com.zkdlu.product;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product orderProdcut(String productId) {
        var product = productRepository.findById(productId)
                .orElseThrow(IllegalAccessError::new);

        product.order(1);

        return product;
    }
}
