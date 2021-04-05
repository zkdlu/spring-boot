package com.zkdlu.tdd.domain.order;

import com.zkdlu.tdd.domain.shop.Shop;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//1. 구매 할 메뉴를 골라 주문을 하면 주문 번호를 발급해준다.
//        2. 해당 주문번호에 결제를 하면, 배송 상태가 된다.
//        3. 배송이 완료 되면 주문이 완료 처리 된다.

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "ORDERS")
public class Order {
    enum OrderState { 결제대기, 수락대기, 조리중, 배송중, 완료, 취소 }

    @Id
    @Column(name = "ORDER_ID")
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ORDER_STATE")
    private OrderState state;

    @ManyToOne
    @JoinColumn(name = "SHOP_ID")
    private Shop shop;

    @OneToMany
    @JoinColumn(name = "ORDER_ID")
    private List<OrderItem> orderItems = new ArrayList<>();

    @Builder
    public Order(String id) {
        this.id = id;
    }

    public void place() {
        verifyPlace();
        state = OrderState.결제대기;
    }

    public void accept() {
        state = OrderState.조리중;
    }

    public void payed() {
        state = OrderState.수락대기;
    }

    public void delivery() {
        state = OrderState.배송중;
    }

    public void complete() {
        state = OrderState.완료;
    }

    public void cancel() {
        state = OrderState.취소;
    }

    private void verifyPlace() {
        if (!shop.isOpen()) {
            throw new IllegalStateException("가게가 영업중이 아닙니다.");
        }

        if (!shop.verifyOrderMoney(getOrderTotalPrice())) {
            throw new IllegalStateException("최소 주문 금액이 부족합니다..");
        }

        orderItems.forEach(OrderItem::verify);
    }

    private int getOrderTotalPrice() {
        return orderItems.stream()
                .map(OrderItem::getMoney)
                .reduce(Integer::sum)
                .orElseThrow(IllegalStateException::new);
    }
}
