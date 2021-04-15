package com.zkdlu.display;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class DisplayService {
    private final RestTemplate restTemplate;

    public DisplayService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Product> getProducts() {
        var products = restTemplate.getForObject("http://localhost:8081/products", Product[].class);

        return Arrays.asList(products);
    }
}
