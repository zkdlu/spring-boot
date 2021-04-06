package com.zkdlu.tdd.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zkdlu.tdd.domain.shop.Menu;
import com.zkdlu.tdd.domain.shop.Shop;
import com.zkdlu.tdd.service.OrderDto;
import com.zkdlu.tdd.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({OrderApi.class})
class OrderApiTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("주문 요청에 필요한 Parameter가 잘 매핑되었나요?")
    void placeOrder() throws Exception {
        //given
        OrderDto orderDto = new OrderDto();
        orderDto.setId("order-1");
        orderDto.setShopId("shop-1");
        orderDto.setMenuIds(Arrays.asList("menu-1","menu-2","menu-3"));
        given(orderService.placeOrder(orderDto)).willReturn("order-1");

        //when
        //then
        mockMvc.perform(
                post("/")
                .content(objectMapper.writeValueAsString(orderDto))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());
    }
}