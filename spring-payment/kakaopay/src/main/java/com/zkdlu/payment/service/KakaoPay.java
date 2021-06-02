package com.zkdlu.payment.service;

import com.zkdlu.order.domain.Order;
import com.zkdlu.order.domain.OrderItem;
import com.zkdlu.payment.domain.Payment;
import com.zkdlu.payment.domain.PaymentRepository;
import com.zkdlu.payment.service.remote.PayReady;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;
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

    @Transactional(propagation = Propagation.REQUIRED)
    public PayReady prepare(Order order) {
        log.info("pay: {}", Thread.currentThread().getId());

        Payment payment = Payment.builder()
                .id(UUID.randomUUID().toString())
                .order(order)
                .build();

        //paymentRepository.save(payment);

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
        params.add("partner_order_id", payment.getId());
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

//    public String kakaoPayReady() {
//
//        // 서버로 요청할 Header
//        HttpHeaders headers = new HttpHeaders();;
//        headers.add("Authorization", "KakaoAK " + kakaoPayAdminKey);
//        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
//        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
//
//        // 서버로 요청할 Body
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
//        params.add("cid", "TC0ONETIME");
//        params.add("partner_order_id", "1001");
//        params.add("partner_user_id", "gorany");
//        params.add("item_name", "건이의 사랑");
//        params.add("quantity", "1");
//        params.add("total_amount", "1000000");
//        params.add("tax_free_amount", "100");
//        params.add("approval_url", "http://localhost:8080/kakaoPaySuccess");
//        params.add("cancel_url", "http://localhost:8080/kakaoPayCancel");
//        params.add("fail_url", "http://localhost:8080/kakaoPaySuccessFail");
//
//        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
//
//        try {
//            KakaoPayReadyVO kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyVO.class);
//
//            return kakaoPayReadyVO.getNext_redirect_pc_url();
//
//        } catch (RestClientException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (URISyntaxException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return "/pay";
//
//    }
}
