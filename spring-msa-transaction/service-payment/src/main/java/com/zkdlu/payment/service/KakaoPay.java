package com.zkdlu.payment.service;

import com.zkdlu.payment.domain.Payment;
import com.zkdlu.payment.domain.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
        HttpHeaders headers = kakaoPayHeader();
        MultiValueMap<String, String> params = kakaoPayParams();

        Payment payment = Payment.builder()
                .id(UUID.randomUUID().toString())
                .productId(productId)
                .price(price)
                .build();

        paymentRepository.save(payment);

        return payment;
    }

    private MultiValueMap<String, String> kakaoPayParams(String productId, int price) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", "gorany");
        params.add("item_name", "건이의 사랑");
        params.add("quantity", "1");
        params.add("total_amount", String.valueOf(price));
        params.add("tax_free_amount", "1");
        params.add("approval_url", "http://localhost:8080/kakaoPaySuccess");
        params.add("cancel_url", "http://localhost:8080/kakaoPayCancel");
        params.add("fail_url", "http://localhost:8080/kakaoPaySuccessFail");

        return params;
    }

    private HttpHeaders kakaoPayHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + kakaoPayAdminKey);
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        return headers;
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
