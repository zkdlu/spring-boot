package com.zkdlu.display;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DisplayController {
    private final DisplayService displayService;

    public DisplayController(DisplayService displayService) {
        this.displayService = displayService;
    }

    @GetMapping("/display")
    public List<Product> getProducts() {
        return displayService.getProducts();
    }

    @GetMapping("/order/{productId}")
    public Product orderProduct(@PathVariable String productId) {
        return null;
    }
}
