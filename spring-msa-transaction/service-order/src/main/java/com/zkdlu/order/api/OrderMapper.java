package com.zkdlu.order.api;

import com.zkdlu.order.service.OrderDto;
import com.zkdlu.order.service.OrderItemDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDto toOrder(Cart cart) {
        var order = new OrderDto();

        order.getProducts().addAll(cart.getItems().stream()
                .map(i -> new OrderItemDto(i.getId(), i.getName(), i.getPrice()))
                .collect(Collectors.toList()));

        return order;
    }
}
