package com.zkdlu.order.service;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderDto {
    private List<OrderItemDto> products = new ArrayList<>();
}
