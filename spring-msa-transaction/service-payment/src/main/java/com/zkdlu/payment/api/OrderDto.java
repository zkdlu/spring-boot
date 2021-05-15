package com.zkdlu.payment.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class OrderDto {
    private String id;
    private List<OrderLineItemDto> orderItems;
}
