package com.zkdlu.hexagonal.orderapp.adapter.out.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRecordRepository extends JpaRepository<OrderRecord, Long> {
}
