package com.zkdlu.payment.service;

import com.zkdlu.payment.domain.Payment;
import com.zkdlu.payment.domain.PaymentRepository;
import com.zkdlu.payment.service.remote.PayReady;
import com.zkdlu.payment.service.remote.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
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
    public PayReady prepare(String productId) {
        Product product = getProductInfo(productId);
        Payment payment = Payment.builder()
                .id(UUID.randomUUID().toString())
                .product(product)
                .build();

        paymentRepository.save(payment);

        var payReady = prepareKakaoPay(payment);

        return payReady;
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

    private PayReady prepareKakaoPay(Payment payment) {
        HttpHeaders headers = kakaoPayHeader();
        MultiValueMap<String, String> params = kakaoPayParams(payment);

        return restTemplate.postForObject(HOST + "/v1/payment/ready",
                new HttpEntity<MultiValueMap<String, String>>(params, headers), PayReady.class);
    }

    private MultiValueMap<String, String> kakaoPayParams(Payment payment) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        // 테스트 결제용 가맹점 코드
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", payment.getId());
        params.add("partner_user_id", "zkdlu");
        params.add("item_name", payment.getProduct().getName());
        params.add("quantity", "1");
        params.add("total_amount", payment.getProduct().getPrice() + "");
        params.add("tax_free_amount", "1");
        params.add("approval_url", "http://localhost:8082/pay/success");
        params.add("cancel_url", "http://localhost:8082/pay/cancel");
        params.add("fail_url", "http://localhost:8082/pay/fail");

        return params;
    }

    private HttpHeaders kakaoPayHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + kakaoPayAdminKey);
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        return headers;
    }

    private Product getProductInfo(String productId) {
        return restTemplate.getForObject("http://localhost:8081/products/detail/" + productId,
                Product.class);
    }
}
