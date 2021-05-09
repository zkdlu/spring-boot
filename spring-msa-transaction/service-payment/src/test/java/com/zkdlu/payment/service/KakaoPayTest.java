package com.zkdlu.payment.service;

import com.zkdlu.payment.domain.Payment;
import com.zkdlu.payment.domain.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class KakaoPayTest {
    @Mock
    PaymentRepository paymentRepository;
    @InjectMocks
    KakaoPay kakaoPay;

    @Test
    void prepare() {
        //given
        //when
        var result = kakaoPay.prepare("product-1", 1000);

        //then
        assertThat(result.getState()).isEqualTo(Payment.State.PREPARE);
    }

    @Test
    void pay() {
        //given
        var payment = kakaoPay.prepare("product-1", 1000);
        given(paymentRepository.findById(payment.getId())).willReturn(Optional.of(payment));

        //when
        kakaoPay.pay(payment.getId());

        //then
        assertThat(payment.getState()).isEqualTo(Payment.State.PAYED);
    }
}