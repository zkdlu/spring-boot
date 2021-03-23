package com.zkdlu.event.order.service;

import com.zkdlu.event.order.domain.Order;
import com.zkdlu.event.order.domain.OrderRepository;
import com.zkdlu.event.purchase.service.PurchaseService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    @MockBean
    private OrderRepository orderRepository;
    @SpyBean
    private PurchaseService purchaseService;

    @Test
    @DisplayName("주문에서 이벤트를 발생해서 결제 요청이 잘 되는가?")
    void payTest() {
        //given
        long orderId = 1L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(new Order(orderId)));

        //when
        orderService.pay(orderId);

        //then
        verify(purchaseService, times(1)).purchase(orderId);
    }
}