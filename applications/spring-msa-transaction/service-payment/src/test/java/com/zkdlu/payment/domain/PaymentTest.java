package com.zkdlu.payment.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    @Test
    void paymentTest() {
        Payment payment = Payment.builder()
                .id("pay-1")
                .build();

        assertThat(payment.getState()).isEqualTo(Payment.State.PREPARE);
    }

    @Test
    void payedTest() {
        Payment payment = Payment.builder()
                .id("pay-1")
                .build();

        payment.pay();

        assertThat(payment.getState()).isEqualTo(Payment.State.PAYED);
    }

    @Test
    void completeTest() {
        Payment payment = Payment.builder()
                .id("pay-1")
                .build();

        payment.complete();

        assertThat(payment.getState()).isEqualTo(Payment.State.COMPLETE);
    }
}