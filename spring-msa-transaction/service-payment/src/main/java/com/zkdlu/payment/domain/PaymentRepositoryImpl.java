package com.zkdlu.payment.domain;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository{
    private Set<Payment> payments = new LinkedHashSet<>();

    @Override
    public List<Payment> findAll() {
        return new ArrayList<>(payments);
    }

    @Override
    public Optional<Payment> findById(String paymentId) {
        return payments.stream()
                .filter(p -> p.getId().equals(paymentId))
                .findFirst();
    }

    @Override
    public Payment save(Payment payment) {
        payments.add(payment);

        return payment;
    }
}
