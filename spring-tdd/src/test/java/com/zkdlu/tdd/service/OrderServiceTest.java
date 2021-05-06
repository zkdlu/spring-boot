package com.zkdlu.tdd.service;

import com.zkdlu.tdd.domain.order.Order;
import com.zkdlu.tdd.domain.order.OrderItem;
import com.zkdlu.tdd.domain.order.OrderRepository;
import com.zkdlu.tdd.domain.shop.Menu;
import com.zkdlu.tdd.domain.shop.Shop;
import com.zkdlu.tdd.domain.shop.ShopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ShopRepository shopRepository;
    @InjectMocks
    private OrderService orderService;

    private Shop shop;
    private OrderDto orderDto;

    @BeforeEach
    void setUp() {
        shop = new Shop("shop-1", "건s shop", 6000);

        shop.addMenu(new Menu("menu-1", "만두", 1000));
        shop.addMenu(new Menu("menu-2", "라면", 2000));
        shop.addMenu(new Menu("menu-3", "고기", 3000));
        shop.open();

        orderDto = new OrderDto();
        orderDto.setId("order-1");
        orderDto.setShopId(shop.getId());
        orderDto.setMenuIds(shop.getMenus().stream().map(Menu::getName).collect(Collectors.toList()));
    }

    Order getOrder() {
        var orderItems = shop.getMenus().stream()
                .map(OrderItem::new)
                .collect(Collectors.toList());

        return Order.builder()
                .id(orderDto.getId())
                .shop(shop)
                .orderItems(orderItems)
                .build();
    }

    @Test
    @DisplayName("shop의 모든 메뉴를 선택하여 주문 요청을 한다.")
    void placeOrder() {
        //given
        given(shopRepository.findById("shop-1")).willReturn(Optional.of(shop));

        //when
        String orderId = orderService.placeOrder(orderDto);

        //then
        assertThat(orderId).isEqualTo("order-1");
    }

    @Test
    @DisplayName("주문번호에 해당하는 주문을 결제한다.")
    void payedOrder() {
        //given
        Order order = getOrder();
        order.place();
        given(orderRepository.findById("order-1")).willReturn(Optional.of(order));

        //when
        String orderId = orderService.payedOrder("order-1");

        //then
        assertThat(orderId).isEqualTo("order-1");
    }

    @Test
    @DisplayName("점주는 해당 주문을 수락한다.")
    void acceptOrder() {
        //given
        Order order = getOrder();
        order.place();
        order.payed();
        given(orderRepository.findById("order-1")).willReturn(Optional.of(order));

        //when
        String orderId = orderService.acceptOrder("order-1");

        //then
        assertThat(order.getState()).isEqualTo(Order.OrderState.조리중);
    }

    @Test
    @DisplayName("주문번호에 해당하는 주문 배송을 시작한다.")
    void deliveryOrder() {
        //given
        Order order = getOrder();
        order.place();
        order.payed();
        order.accept();
        given(orderRepository.findById("order-1")).willReturn(Optional.of(order));

        //when
        String orderId = orderService.deliveryOrder("order-1");

        //then
        assertThat(order.getState()).isEqualTo(Order.OrderState.배송중);
    }

    @Test
    @DisplayName("주문자는 물건을 받고 주문 완료를 누른다.")
    void completeOrder() {
        //given
        Order order = getOrder();
        order.place();
        order.payed();
        order.accept();
        order.delivery();
        given(orderRepository.findById("order-1")).willReturn(Optional.of(order));

        //when
        String orderId = orderService.completeOrder("order-1");

        //then
        assertThat(order.getState()).isEqualTo(Order.OrderState.완료);
    }

    @Test
    @DisplayName("결제 전에 주문을 취소한다.")
    void cancelOrder() {
        //given
        Order order = getOrder();
        order.place();
        given(orderRepository.findById("order-1")).willReturn(Optional.of(order));

        //when
        String orderId = orderService.cancelOrder("order-1");

        //then
        assertThat(order.getState()).isEqualTo(Order.OrderState.취소);
    }

    @Test
    @DisplayName("실패하는 테스트 케이스")
    void failTest() {
        assertThat(true).isEqualTo(false);
    }
}