package com.zkdlu.event;

import com.zkdlu.event.order.service.OrderRequest;
import com.zkdlu.event.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(EventApplication.class, args);
    }

    @Autowired
    private OrderService orderService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        OrderRequest orderRequest = new OrderRequest(1L);

        orderService.placeOrder(orderRequest);
    }
}
