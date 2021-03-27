package com.zkdlu.hexagonal.orderapp.application.order.port.out;

import com.zkdlu.hexagonal.orderapp.domain.order.Receipt;

public interface RecordOrderPort {
    void recordOrder(Receipt receipt);
}
