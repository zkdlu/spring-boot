package com.zkdlu.feign.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DemoApi {
    @GetMapping("/demo1")
    public Map demo1() {
        Map<String, String> map = new HashMap();
        map.put("content", "demo");

        return map;
    }

    @GetMapping("/demo2")
    public Map demo2() {
        Map<String, String> map = new HashMap();
        map.put("test", "test");

        return map;
    }

    @GetMapping("/demo3")
    public Map demo3() {
        throw new RuntimeException();
    }

    @GetMapping("/demo4")
    public Map demo4() {
        Map<String, String> map = new HashMap();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return map;
    }
}
