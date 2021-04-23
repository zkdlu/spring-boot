package com.zkdlu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Controller
public class PaymentController {
    @GetMapping("/")
    public String test() {
        return "test.html";
    }

    @PostMapping("/payment")
    public String callback(@RequestBody Map map) {
        return "test";
    }
}
