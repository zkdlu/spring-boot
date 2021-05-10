package com.zkdlu.payment.api;

import com.zkdlu.payment.service.PayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Slf4j
@CrossOrigin("*")
@RequiredArgsConstructor
@Controller
public class PayController {
    private final PayService payService;

    @PostMapping("/pay")
    public RedirectView kakaoPay(@RequestBody PayRequest payRequest) throws IOException {
        var payReadyUrl  = payService.prepare(payRequest.getProductId());
        log.info(payReadyUrl);

        return new RedirectView(payReadyUrl);
    }
}
