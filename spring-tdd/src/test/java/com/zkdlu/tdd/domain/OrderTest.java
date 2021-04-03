package com.zkdlu.tdd.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private Order order;
    @BeforeEach
    void setUp() {
        order = new Order(UUID.randomUUID().toString(), "item",  1000);
    }

    @Test
    @DisplayName("주문 테스트")
    void place() {
        //given
        //when
        order.place();

        //then
        assertThat(order.getState()).isEqualTo(Order.OrderState.PREPARE);
    }

    @Test
    @DisplayName("결제 테스트")
    void payed() {
        //given
        //when
        order.payed();

        //then
        assertThat(order.getState()).isEqualTo(Order.OrderState.PAYED);
    }

    @Test
    @DisplayName("완료 테스트")
    void complete() {
        //given
        //when
        order.complete();

        //then
        assertThat(order.getState()).isEqualTo(Order.OrderState.COMPLETE);
    }
}