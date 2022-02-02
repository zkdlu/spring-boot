package com.zkdlu.payment.service;

import com.zkdlu.payment.domain.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

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
        //then
    }

    @Test
    void pay() {
        //given
        //when
        // then
    }
}