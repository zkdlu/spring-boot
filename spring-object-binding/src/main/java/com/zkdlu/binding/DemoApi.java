package com.zkdlu.binding;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoApi {

    @PostMapping("/requestBody")
    public void postWithRequestBody() {

    }
}
