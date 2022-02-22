package com.zkdlu.api.payment;

import com.zkdlu.domain.order.Order;
import com.zkdlu.domain.payment.KakaoPay;
import com.zkdlu.domain.payment.remote.PayReady;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/pay")
@RestController
public class PaymentApi {
    private final KakaoPay kakaopay;

    @PostMapping
    public PayReady pay(@RequestBody Order order, HttpServletRequest request) throws IOException {
        var payReady  = kakaopay.prepare(order);
        log.info(payReady.getTid());

        var session = request.getSession(true);
        session.setAttribute(order.getId().toString(), payReady);

        return payReady;
    }


    @GetMapping("/success")
    public String paySuccess(@RequestParam("pg_token") String pg_token,
                             @RequestParam("order") Long orderId,
                             HttpSession session) {
        log.info("paySuccess pg_token : " + pg_token);

        var payReady = (PayReady)session.getAttribute("payReady");

        return kakaopay.approve(pg_token, orderId, payReady);
    }

    @GetMapping("/cancel")
    public String payCancel() {
        log.info("payCancel get............................................");

        return "cancel";
    }

    @GetMapping("/fail")
    public String payFail() {
        log.info("payFail get............................................");

        return "fail";
    }
}
