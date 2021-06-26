package com.zkdlu.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    @DisplayName("주문수량 확인")
    void 주문수량_확인() {
        //given
        Product product = new Product("1", 1000, 10);
        //when
        product.order(5);
        //then
        assertThat(product.getStock()).isEqualTo(5);
    }

    @Test
    @DisplayName("초과주문")
    void 초과주문() {
        //given
        Product product = new Product("1", 1000, 10);

        //when
        var exception = assertThrows(IllegalStateException.class,
                () -> product.order(11));

        //then
    }
}