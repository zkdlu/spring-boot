package com.zkdlu.hexagonal.orderapp.application.order.service;

import com.zkdlu.hexagonal.orderapp.application.order.port.in.GetReceiptUseCase;
import com.zkdlu.hexagonal.orderapp.application.order.port.in.ReceiptResult;
import com.zkdlu.hexagonal.orderapp.application.order.port.out.GetOrderRecordPort;
import com.zkdlu.hexagonal.orderapp.application.order.port.out.OrderRecord;
import com.zkdlu.hexagonal.orderapp.domain.order.Receipt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetReceiptService implements GetReceiptUseCase {
    private final GetOrderRecordPort orderRecordPort;
    @Override
    public ReceiptResult getReceipt(String orderId) {
        OrderRecord orderRecord = orderRecordPort.getOrder(orderId);
        Receipt receipt = new Receipt(orderRecord.getOrderId(), orderRecord.getMoney());

        return new ReceiptResult(receipt.getOrderId(), receipt.getMoney());
    }
}
