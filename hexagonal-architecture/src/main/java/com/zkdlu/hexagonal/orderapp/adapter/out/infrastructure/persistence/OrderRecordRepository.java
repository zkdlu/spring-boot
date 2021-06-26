package com.zkdlu.hexagonal.orderapp.adapter.out.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRecordRepository extends JpaRepository<OrderRecordEntity, Long> {
    public Optional<OrderRecordEntity> findByOrderId(String orderId);
}
