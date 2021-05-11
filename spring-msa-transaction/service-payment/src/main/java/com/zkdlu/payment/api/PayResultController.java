package com.zkdlu.payment.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PayResultController {
    @GetMapping("/pay/success")
    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token) {
        return "success.html";
    }

    @GetMapping("/pay/fail")
    public String kakaoPayFail() {
        return "faile";
    }

    @GetMapping("/pay/cancel")
    public String kakaoPayCancel() {
        return "cancel";
    }
}
