package com.zkdlu.product.api;

import com.zkdlu.product.domain.Product;
import com.zkdlu.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class ProductApi {
    private final ProductService productService;

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/products/{page}")
    public List<Product> getProducts(@PathVariable int page) {
        return productService.getProductsByPage(page);
    }
}
