package com.zkdlu.display;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "product",
        fallback = FeignProductServiceFallbackImpl.class)
public interface FeignProductService {
    @RequestMapping(path = "/products")
    List<Product> getProducts();
}
