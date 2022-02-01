package com.zkdlu.json.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoApi {
    @GetMapping("/demo")
    public Demo getDemo() {
        return new Demo(1, "str", true, 1f);
    }
}
