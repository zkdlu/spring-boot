package com.zkdlu.payment.api;

import com.zkdlu.payment.service.KakaoPay;
import com.zkdlu.payment.service.PayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin("*")
@RequiredArgsConstructor
@Controller
public class PayController {
    private final PayService payService;

    @PostMapping("/pay")
    public RedirectView kakaoPay(@RequestBody PayRequest payRequest) throws IOException {
        payService.prepare(payRequest.getProductId());
        return new RedirectView("http://localhost:8080/");
    }
}
