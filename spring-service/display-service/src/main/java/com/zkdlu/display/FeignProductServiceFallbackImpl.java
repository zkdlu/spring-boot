package com.zkdlu.display;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class FeignProductServiceFallbackImpl implements FeignProductService{
    @Override
    public List<Product> getProducts() {
        return Collections.singletonList(Product.Empty);
    }

    @Override
    public Product orderProduct(String productId) {
        return Product.Empty;
    }
}
