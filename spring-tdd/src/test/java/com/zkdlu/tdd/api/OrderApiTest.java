package com.zkdlu.tdd.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zkdlu.tdd.domain.shop.Menu;
import com.zkdlu.tdd.domain.shop.Shop;
import com.zkdlu.tdd.service.OrderDto;
import com.zkdlu.tdd.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
    void placeOrder() throws Exception {
        //given
        Shop shop = new Shop("shop-1", "건s shop", 6000);

        shop.addMenu(new Menu("menu-1", "만두", 1000));
        shop.addMenu(new Menu("menu-2", "라면", 2000));
        shop.addMenu(new Menu("menu-3", "고기", 3000));
        shop.open();

        OrderDto orderDto = new OrderDto();
        orderDto.setId("order-1");
        orderDto.setShopId(shop.getId());
        orderDto.setMenuIds(shop.getMenus().stream().map(Menu::getName).collect(Collectors.toList()));
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