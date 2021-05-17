package com.zkdlu.payment.api;

import com.zkdlu.payment.service.PayService;
import com.zkdlu.payment.service.remote.Order;
import com.zkdlu.payment.service.remote.PayReady;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public PayReady kakaoPay(@RequestBody Order order, HttpServletRequest request) throws IOException {
        var payReady  = payService.prepare(order);
        log.info(payReady.getTid());

        var session = request.getSession(true);
        session.setAttribute("pay.ready", payReady);

        return payReady;
    }

    @GetMapping("/pay/success")
    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token,
                                  HttpServletRequest request) {
        var session = request.getSession(false);
        PayReady payReady = (PayReady) session.getAttribute("pay.ready");

        log.info(pg_token + " : " + payReady.getTid());

        return "redirect:localhost:8080";
    }

    @GetMapping("/pay/fail")
    @ResponseBody
    public String kakaoPayFail() {
        return "faile";
    }

    @GetMapping("/pay/cancel")
    @ResponseBody
    public String kakaoPayCancel() {
        return "cancel";
    }
}
