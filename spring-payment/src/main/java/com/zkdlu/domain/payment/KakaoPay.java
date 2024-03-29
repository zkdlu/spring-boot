package com.zkdlu.domain.payment;

import com.zkdlu.domain.order.Order;
import com.zkdlu.domain.order.OrderItem;
import com.zkdlu.domain.payment.remote.PayReady;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class KakaoPay {
    private static final String HOST = "https://kapi.kakao.com";

    @Value("${kakao.admin}")
    private String kakaoPayAdminKey;

    private final RestTemplate restTemplate;
    private final PaymentRepository paymentRepository;

    @Transactional
    public String approve(String pg_token, Long orderId, PayReady payReady) {
        var approve = approveKakaoPay(pg_token, orderId, payReady.getTid());

        Payment payment = paymentRepository.findById(orderId).orElseThrow(IllegalStateException::new);
        payment.payed();

        return approve;
    }

    private String approveKakaoPay(String pgToken, Long orderId, String tid) {
        HttpHeaders headers = kakaoPayHeader();

        MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();

        params.add("cid", "TC0ONETIME");
        params.add("tid", tid);
        params.add("partner_order_id", orderId);
        params.add("partner_user_id", "zkdlu");
        params.add("pg_token", pgToken);

        return restTemplate.postForObject(HOST + "/v1/payment/approve",
                new HttpEntity<MultiValueMap<String, Object>>(params, headers), String.class);
    }

    @Transactional
    public PayReady prepare(Order order) {
        log.info("pay: {}", Thread.currentThread().getId());

        Payment payment = Payment.builder()
                .id(order.getId())
                .order(order)
                .build();

        paymentRepository.save(payment);

        return prepareKakaoPay(payment);
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
        params.add("partner_order_id", payment.getOrder().getId().toString());
        params.add("partner_user_id", "zkdlu");
        params.add("item_name", payment.getOrder().getOrderItems().stream().map(OrderItem::getName).collect(Collectors.joining(",")));
        params.add("quantity", "1");
        params.add("total_amount", payment.getOrder().getOrderItems().stream().map(OrderItem::getPrice).reduce(0, Integer::sum).toString());
        params.add("tax_free_amount", "1");
        params.add("approval_url", "http://localhost:8080/pay/success?order=" + payment.getId());
        params.add("cancel_url", "http://localhost:8080/pay/cancel");
        params.add("fail_url", "http://localhost:8080/pay/fail");

        return params;
    }

    private HttpHeaders kakaoPayHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + kakaoPayAdminKey);
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        return headers;
    }
}