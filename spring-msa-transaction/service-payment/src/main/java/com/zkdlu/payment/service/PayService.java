package com.zkdlu.payment.service;

import com.zkdlu.payment.domain.Payment;
import com.zkdlu.payment.service.remote.Order;
import com.zkdlu.payment.service.remote.PayReady;

public interface PayService {
    PayReady prepare(Order order);
    void pay(String paymentId);
    void complete(String paymentId);
}
