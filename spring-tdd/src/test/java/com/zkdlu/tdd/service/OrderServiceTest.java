package com.zkdlu.tdd.service;

import com.zkdlu.tdd.domain.Order;
import com.zkdlu.tdd.domain.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    class FakeOrderRepository extends OrderRepository {

    }

    private OrderRepository orderRepository = new FakeOrderRepository();
    private OrderService orderService = new OrderService(orderRepository);

    @Test
    void placeOrder() {
        //given
        OrderDto orderDto = new OrderDto();
        orderDto.setItem("item");
        orderDto.setPrice(100);

        //when
        String id = orderService.placeOrder(orderDto);

        //then
        Order order = orderRepository.findById(id);
        assertThat(order.getId()).isEqualTo(id);
    }

    @Test
    void payedOrder() {
        //given

        //when

        //then
    }

    @Test
    void completeOrder() {
        //given

        //when

        //then
    }
}