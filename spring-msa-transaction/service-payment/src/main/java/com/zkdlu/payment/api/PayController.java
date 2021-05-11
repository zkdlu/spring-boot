package com.zkdlu.payment.api;

import com.zkdlu.payment.service.PayService;
import com.zkdlu.payment.service.remote.PayReady;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Slf4j
@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class PayController {
    private final PayService payService;

    @PostMapping("/pay")
    public PayReady kakaoPay(@RequestBody PayRequest payRequest) throws IOException {
        var payReady  = payService.prepare(payRequest.getProductId());
        log.info(payReady.getTid());

        return payReady;
    }
}
