package com.zkdlu.hexagonal.orderapp.application.order.port.out;

public interface GetOrderRecordPort {
    OrderRecord getOrder(String orderId);
}