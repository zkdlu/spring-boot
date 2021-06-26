package com.zkdlu.domain.product;

import com.zkdlu.domain.product.Product;
import com.zkdlu.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByPage(int page) {
        return productRepository.findAll(PageRequest.of(page, 42))
                .toList();
    }

    public Product getProductDetail(Long id) {
        return productRepository.findById(id)
                .orElseThrow(IllegalAccessError::new);
    }
}
