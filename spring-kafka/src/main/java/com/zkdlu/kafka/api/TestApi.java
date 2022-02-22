package com.zkdlu.kafka.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class TestApi {
    private static Random random = new Random();
    @GetMapping("/")
    public String test() {
        if (random.nextInt(2) < 1) {
            throw new RuntimeException();
        }
        return "Hello world";
    }
}

