package com.zkdlu.hexagonal.orderapp.application.order.service;

import com.zkdlu.hexagonal.orderapp.application.order.port.in.OrderDetail;
import com.zkdlu.hexagonal.orderapp.application.order.port.in.OrderResult;
import com.zkdlu.hexagonal.orderapp.application.order.port.in.PlaceOrderUseCase;
import com.zkdlu.hexagonal.orderapp.application.order.port.out.RecordOrderPort;
import com.zkdlu.hexagonal.orderapp.domain.order.Order;
import com.zkdlu.hexagonal.orderapp.domain.order.Receipt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceOrderService implements PlaceOrderUseCase {
    private final RecordOrderPort recordOrderPort;

    @Override
    public OrderResult placeOrder(OrderDetail orderDetail) {
        Order order = new Order(orderDetail.getOrderId(), orderDetail.getMoney());
        order.place();

        Receipt receipt = order.printReceipt();
        recordOrderPort.recordOrder(receipt);

        return new OrderResult(order.getId(), order.getMoney());
    }
}
