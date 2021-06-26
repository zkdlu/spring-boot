package com.zkdlu.payment.api;

import com.zkdlu.payment.service.PayService;
import com.zkdlu.payment.service.remote.Order;
import com.zkdlu.payment.service.remote.PayReady;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
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
        session.setAttribute(order.getId(), payReady);

        return payReady;
    }

    @GetMapping("/pay/success")
    @ResponseBody
    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token,
                                        @RequestParam String order) {

        log.info(pg_token + " : " + order);

        return pg_token + " : " + order;
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
