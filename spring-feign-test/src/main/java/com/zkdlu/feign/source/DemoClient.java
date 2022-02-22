package com.zkdlu.feign.source;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name = "demoClient", url = "http://localhost:8080", fallback = DemoClientFallback.class)
public interface DemoClient {
    @GetMapping("/demo1")
    Demo demo1();

    @GetMapping("/demo2")
    Demo demo2();

    @GetMapping("/demo3")
    Demo demo3();

    @GetMapping("/demo4")
    Demo demo4();
}
