package com.zkdlu.hexagonal.orderapp.application.order.port.in;

public interface GetReceiptUseCase {
    ReceiptResult getReceipt(String orderId);
}
