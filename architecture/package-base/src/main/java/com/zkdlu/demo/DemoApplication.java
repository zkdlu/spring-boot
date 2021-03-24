package com.zkdlu.demo;

import com.zkdlu.demo.domain.order.Order;
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
        // Post Order Dto
        Order order = Order.createOrder();
        orderService.placeOrder(order);



    }
}
