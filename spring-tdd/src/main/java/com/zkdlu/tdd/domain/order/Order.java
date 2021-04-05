package com.zkdlu.tdd.domain.order;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//1. 구매 할 메뉴를 골라 주문을 하면 주문 번호를 발급해준다.
//        2. 해당 주문번호에 결제를 하면, 배송 상태가 된다.
//        3. 배송이 완료 되면 주문이 완료 처리 된다.

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "ORDERS")
public class Order {
    enum OrderState { 결제대기, 수락대기, 조리중, 배송중, 주문완료, 취소 }

    @Id
    @Column(name = "ORDER_ID")
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ORDER_STATE")
    private OrderState state;

    @Builder
    public Order(String id) {
        this.id = id;
    }
}
