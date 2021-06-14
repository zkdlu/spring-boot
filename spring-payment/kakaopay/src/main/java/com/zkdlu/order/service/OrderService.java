package com.zkdlu.order.service;

import com.zkdlu.order.domain.Order;
import com.zkdlu.order.domain.OrderItem;
import com.zkdlu.order.domain.OrderRepository;
import com.zkdlu.payment.service.KakaoPay;
import com.zkdlu.payment.service.remote.PayReady;
import com.zkdlu.product.domain.Product;
import com.zkdlu.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final KakaoPay kakaoPay;

    @Transactional
    public PayRequest placeOrder(List<OrderItem> orderItems) {
        log.info("order service: {}", Thread.currentThread().getId());

        for (OrderItem orderItem : orderItems) {
            Product product = productService.getProductDetail(orderItem.getId());
            orderItem.canBuy(product.getStock());
        }

        Order order = Order.builder()
                .id(UUID.randomUUID().toString())
                .orderItems(orderItems)
                .build();

        order.verify();

        orderRepository.save(order);

        PayReady payReady = kakaoPay.prepare(order);

        return PayRequest.builder()
                .payReady(payReady)
                .order(order)
                .build();
    }
}
