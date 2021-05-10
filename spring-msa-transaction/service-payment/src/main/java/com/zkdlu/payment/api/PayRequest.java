package com.zkdlu.payment.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PayRequest {
    @JsonProperty("product_id")
    private String productId;
}
