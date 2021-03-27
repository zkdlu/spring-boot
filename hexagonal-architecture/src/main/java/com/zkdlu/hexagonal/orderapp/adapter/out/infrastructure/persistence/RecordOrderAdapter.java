package com.zkdlu.hexagonal.orderapp.adapter.out.infrastructure.persistence;

import com.zkdlu.hexagonal.orderapp.application.order.port.out.RecordOrderPort;
import com.zkdlu.hexagonal.orderapp.domain.order.Receipt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class RecordOrderAdapter implements RecordOrderPort {
    private final OrderRecordRepository orderRecordRepository;

    @Override
    public void recordOrder(Receipt receipt) {
        System.out.println(receipt.getMoney());
        orderRecordRepository.save(OrderRecord.from(receipt));
    }
}
