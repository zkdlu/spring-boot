package com.zkdlu.payment.api;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OrderLineItemDto {
    private String id;
    private String name;
    private int price;
}
