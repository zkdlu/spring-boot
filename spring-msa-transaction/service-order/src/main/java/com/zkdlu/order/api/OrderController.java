package com.zkdlu.order.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin("*")
@RestController
public class OrderController {
    @PostMapping("/order")
    public String order(@RequestBody Cart cart) {
        log.info("hello");
        return "hello";
    }
}
