package com.zkdlu.hexagonal.orderapp.adapter.out.infrastructure.persistence;

import com.zkdlu.hexagonal.orderapp.application.order.port.out.GetOrderRecordPort;
import com.zkdlu.hexagonal.orderapp.application.order.port.out.OrderRecord;
import com.zkdlu.hexagonal.orderapp.application.order.port.out.RecordOrderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class RecordOrderAdapter implements RecordOrderPort, GetOrderRecordPort {
    private final OrderRecordRepository orderRecordRepository;

    @Override
    public void recordOrder(OrderRecord orderRecord) {
        orderRecordRepository.save(OrderRecordEntity.from(orderRecord));
    }

    @Override
    public OrderRecord getOrder(String orderId) {
        OrderRecordEntity orderRecord = orderRecordRepository.findByOrderId(orderId).orElseThrow(IllegalAccessError::new);

        return new OrderRecord(orderRecord.getOrderId(), orderRecord.getMoney());
    }
}