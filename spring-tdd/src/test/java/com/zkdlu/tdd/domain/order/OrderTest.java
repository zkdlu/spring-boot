package com.zkdlu.tdd.domain.order;

import com.zkdlu.tdd.domain.shop.Menu;
import com.zkdlu.tdd.domain.shop.Shop;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private Shop shop;

    @BeforeEach
    void setUp() {
        shop = new Shop("shop-1", "건s shop", 3000);

        shop.addMenu(new Menu("menu-1", "만두", 1000));
        shop.addMenu(new Menu("menu-2", "라면", 2000));
        shop.addMenu(new Menu("menu-3", "고기", 3000));

        shop.open();
    }

    @Test
    @DisplayName("주문 정보 생성")
    void place() {
        //given
        List<OrderItem> orderItems = shop.getMenus()
                .stream()
                .filter(m -> m.getPrice() <= 2000)
                .map(m -> new OrderItem(m.getId(), m.getName(), m, 1))
                .collect(Collectors.toList());

        Order order = Order.builder()
                .id("order-id")
                .shop(shop)
                .orderItems(orderItems)
                .build();
        //when
        order.place();

        //then
        assertThat(order.getState()).isEqualTo(Order.OrderState.결제대기);
    }
}