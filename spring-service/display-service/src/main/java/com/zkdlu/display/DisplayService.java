package com.zkdlu.display;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class DisplayService {
    private final RestTemplate restTemplate;
    private final FeignProductService productProxy;

    public DisplayService(RestTemplate restTemplate, FeignProductService productProxy) {
        this.restTemplate = restTemplate;
        this.productProxy = productProxy;
    }

    @HystrixCommand(fallbackMethod = "getProductsFallback")
    public List<Product> getProducts() {
        var products = restTemplate.getForObject("http://localhost:8081/products", Product[].class);

        return Arrays.asList(products);
    }
    
    public List<Product> getProductsFallback() {
        return Collections.singletonList(Product.Empty);
    }
}
