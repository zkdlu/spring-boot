package com.zkdlu.payment.service;

import com.zkdlu.payment.domain.Payment;

public interface PayService {
    Payment prepare(String productId, int price);
    void pay(String paymentId);
    void complete(String paymentId);
}
