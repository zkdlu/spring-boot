package com.zkdlu.demo.controller.order;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @PostMapping
    public String order() {
        return "";
    }
}
