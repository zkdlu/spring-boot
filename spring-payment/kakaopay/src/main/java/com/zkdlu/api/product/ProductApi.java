package com.zkdlu.api.product;

import com.zkdlu.domain.product.Product;
import com.zkdlu.domain.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductApi {
    private final ProductService productService;

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/products/page/{page}")
    public List<Product> getProductPage(@PathVariable int page) {
        return productService.getProductsByPage(page);
    }

    @GetMapping("/products/detail/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProductDetail(id);
    }
}
