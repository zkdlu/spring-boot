package com.zkdlu.payment.domain;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository {
    List<Payment> findAll();
    Optional<Payment> findById(String paymentId);
    Payment save(Payment payment);
}
