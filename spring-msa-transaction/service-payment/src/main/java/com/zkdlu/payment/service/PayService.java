package com.zkdlu.payment.service;

import com.zkdlu.payment.domain.Payment;

public interface PayService {
    String prepare(String productId);
    void pay(String paymentId);
    void complete(String paymentId);
}
