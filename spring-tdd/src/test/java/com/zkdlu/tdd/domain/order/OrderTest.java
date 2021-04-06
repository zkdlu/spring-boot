package com.zkdlu.tdd.domain.order;

import com.zkdlu.tdd.domain.shop.Menu;
import com.zkdlu.tdd.domain.shop.Shop;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderTest {
    private Shop shop;

    @BeforeEach
    void setUp() {
        shop = new Shop("shop-1", "건s shop", 6000);

        shop.addMenu(new Menu("menu-1", "만두", 1000));
        shop.addMenu(new Menu("menu-2", "라면", 2000));
        shop.addMenu(new Menu("menu-3", "고기", 3000));

        shop.open();
    }

    @Test
    @DisplayName("가게가 영업중이고 최소 주문 금액을 만족한다.")
    void placeTest1() {
        //given
        shop.open();
        List<OrderItem> orderItems = shop.getMenus()
                .stream()
                .map(m -> new OrderItem(m.getId(), m.getName(), m, 1))
                .collect(Collectors.toList());

        Order order = Order.builder()
                .id("order-1")
                .shop(shop)
                .orderItems(orderItems)
                .build();
        //when
        order.place();

        //then
        assertThat(order.getState()).isEqualTo(Order.OrderState.결제대기);
    }

    @Test
    @DisplayName("가게가 영업중이 아니고 최소 주문 금액을 만족한다.")
    void placeTest2() {
        //given
        shop.close();
        List<OrderItem> orderItems = shop.getMenus()
                .stream()
                .map(m -> new OrderItem(m.getId(), m.getName(), m, 1))
                .collect(Collectors.toList());

        Order order = Order.builder()
                .id("order-1")
                .shop(shop)
                .orderItems(orderItems)
                .build();
        //when
        //then
        assertThatThrownBy(() -> order.place())
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("가게가 영업중이지만 최소 주문 금액을 만족하지 않는다..")
    void placeTest3() {
        //given
        shop.open();
        List<OrderItem> orderItems = shop.getMenus()
                .stream()
                .filter(o -> o.getName().equals("만두"))
                .map(m -> new OrderItem(m.getId(), m.getName(), m, 1))
                .collect(Collectors.toList());

        Order order = Order.builder()
                .id("order-1")
                .shop(shop)
                .orderItems(orderItems)
                .build();
        //when
        //then
        assertThatThrownBy(() -> order.place())
                .isInstanceOf(IllegalStateException.class);
    }
}