package com.zkdlu.payment.service;

import com.zkdlu.payment.domain.Payment;
import com.zkdlu.payment.domain.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class KakaoPay implements PayService{
    private static final String HOST = "https://kapi.kakao.com";

    @Value("${kakao.admin}")
    private String kakaoPayAdminKey;

    private final PaymentRepository paymentRepository;
    private final RestTemplate restTemplate;

    @Override
    public Payment prepare(String productId, int price) {
        Payment payment = Payment.builder()
                .id(UUID.randomUUID().toString())
                .productId(productId)
                .price(price)
                .build();

        paymentRepository.save(payment);

        return payment;
    }

    @Override
    public void pay(String paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(IllegalStateException::new);

        payment.pay();
    }

    @Override
    public void complete(String paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(IllegalStateException::new);

        payment.complete();
    }
}
