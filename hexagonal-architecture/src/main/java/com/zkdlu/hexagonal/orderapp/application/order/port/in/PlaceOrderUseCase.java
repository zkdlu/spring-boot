package com.zkdlu.hexagonal.orderapp.application.order.port.in;

public interface PlaceOrderUseCase {
    OrderResult placeOrder(OrderDetail orderDetail);
}
