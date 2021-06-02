package com.zkdlu.payment.api;

import com.zkdlu.order.domain.Order;
import com.zkdlu.payment.service.KakaoPay;
import com.zkdlu.payment.service.remote.PayReady;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Controller
public class PaymentApi {
    private final KakaoPay kakaopay;

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @PostMapping("/pay")
    @ResponseBody
    public PayReady kakaoPay(@RequestBody Order order, HttpServletRequest request) throws IOException {
        var payReady  = kakaopay.prepare(order);
        log.info(payReady.getTid());

        var session = request.getSession(true);
        session.setAttribute(order.getId(), payReady);

        return payReady;
    }


    @GetMapping("/kakaoPaySuccess")
    @ResponseBody
    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);

        return "index.html";
    }
}
