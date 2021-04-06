package com.zkdlu.tdd.domain.order;

import com.zkdlu.tdd.domain.shop.Shop;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "ORDERS")
public class Order {
    enum OrderState { 결제대기, 수락대기, 조리중, 배송중, 완료, 취소 }

    @Id
    @Column(name = "ORDER_ID")
    private String id;

    @ManyToOne
    @JoinColumn(name = "SHOP_ID")
    private Shop shop;

    @OneToMany
    @JoinColumn(name = "ORDER_ID")
    private List<OrderItem> orderItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "ORDER_STATE")
    private OrderState state;

    @Builder
    public Order(String id, Shop shop, List<OrderItem> orderItems) {
        this.id = id;
        this.shop = shop;
        this.orderItems = orderItems;
    }

    public void place() {
        verifyPlace();

        state = OrderState.결제대기;
    }

    public void payed() {
        if (state != OrderState.결제대기)
            throw new IllegalStateException("이미 결제 되었습니다.");

        state = OrderState.수락대기;
    }

    public void accept() {
        if (state != OrderState.수락대기)
            throw new IllegalStateException("수락전에만 수락 가능합니다.");

        state = OrderState.조리중;
    }

    public void delivery() {
        if (state != OrderState.조리중)
            throw new IllegalStateException("조리가 완료된 경우에만 배송 가능합니다.");

        state = OrderState.배송중;
    }

    public void complete() {
        if (state != OrderState.배송중)
            throw new IllegalStateException("배송이 완료되어야 합니다.");

        state = OrderState.완료;
    }

    public void cancel() {
        if (state != OrderState.결제대기 && state != OrderState.수락대기)
            throw new IllegalStateException("주문이 수락 된 경우 취소할 수 없습니다.");

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
