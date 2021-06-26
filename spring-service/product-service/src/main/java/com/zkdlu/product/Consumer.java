package com.zkdlu.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    private final Logger log = LoggerFactory.getLogger(Consumer.class);
    private final ProductService productService;

    public Consumer(ProductService productService) {
        this.productService = productService;
    }

    @Value("${kafka.topics.test}")
    private String topic;

    @KafkaListener(topics = "${kafka.topics.test}", groupId = "${spring.kafka.consumer.group-id}")
    void listen(String message) {
        productService.orderProdcut(message);
    }
}
