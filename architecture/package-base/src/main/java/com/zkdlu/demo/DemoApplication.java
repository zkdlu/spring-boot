package com.zkdlu.demo;

import com.zkdlu.demo.domain.order.Order;
import com.zkdlu.demo.domain.shop.Product;
import com.zkdlu.demo.service.order.OrderRequest;
import com.zkdlu.demo.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    OrderService orderService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 사용자의 주문 요청이 Controller Layer로부터 들어온다.
        OrderRequest orderRequest = new OrderRequest(1L, 1L);
        orderService.placeOrder(orderRequest);

        orderService.payOrder(orderRequest.getOrderId());
    }
}
