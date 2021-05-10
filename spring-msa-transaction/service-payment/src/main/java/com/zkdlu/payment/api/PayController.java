package com.zkdlu.payment.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@CrossOrigin("*")
@RestController
public class PayController {
    @PostMapping("/pay")
    public RedirectView kakaoPay(HttpServletResponse response) {
        return new RedirectView("http://localhost:8080/");
    }
}
