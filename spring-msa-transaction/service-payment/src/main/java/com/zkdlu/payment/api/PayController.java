package com.zkdlu.payment.api;

import com.zkdlu.payment.service.PayService;
import com.zkdlu.payment.service.remote.Order;
import com.zkdlu.payment.service.remote.PayReady;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@CrossOrigin("*")
@RequiredArgsConstructor
@Controller
public class PayController {
    private final PayService payService;

    @PostMapping("/pay")
    @ResponseBody
    public PayReady kakaoPay(@RequestBody Order order, HttpSession session) throws IOException {
        var payReady  = payService.prepare(order);
        log.info(payReady.getTid());

        session.setAttribute("pay.ready", payReady);

        return payReady;
    }

    @GetMapping("/pay/success")
    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token,
                                  HttpSession session) {

        PayReady payReady = (PayReady) session.getAttribute("pay.ready");

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
