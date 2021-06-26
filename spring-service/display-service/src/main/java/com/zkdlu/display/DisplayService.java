package com.zkdlu.display;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class DisplayService {
    @Value("${kafka.topics.test}")
    private String topic;

    private final Producer producer;
    private final RestTemplate restTemplate;
    private final FeignProductService productProxy;

    public DisplayService(Producer producer, RestTemplate restTemplate, FeignProductService productProxy) {
        this.producer = producer;
        this.restTemplate = restTemplate;
        this.productProxy = productProxy;
    }

    @HystrixCommand(fallbackMethod = "getProductsFallback")
    public List<Product> getProducts() {
        return productProxy.getProducts();
    }
    
    public List<Product> getProductsFallback() {
        return Collections.singletonList(Product.Empty);
    }

    public String orderProduct(String productId) {
        producer.sendMessage(topic, productId);

        return productId;
    }
}
