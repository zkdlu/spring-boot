package com.zkdlu.payment.service;

import com.zkdlu.payment.domain.Payment;
import com.zkdlu.payment.service.remote.PayReady;

public interface PayService {
    PayReady prepare(String productId);
    void pay(String paymentId);
    void complete(String paymentId);
}
