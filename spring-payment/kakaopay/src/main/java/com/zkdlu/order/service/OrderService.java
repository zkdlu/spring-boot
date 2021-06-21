package com.zkdlu.order.service;

import com.zkdlu.domain.order.Order;
import com.zkdlu.domain.order.OrderItem;
import com.zkdlu.domain.order.OrderRepository;
import com.zkdlu.order.events.OrderEvent;
import com.zkdlu.payment.service.KakaoPay;
import com.zkdlu.payment.service.remote.PayReady;
import com.zkdlu.product.domain.Product;
import com.zkdlu.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {
    private final ApplicationEventPublisher eventPublisher;
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final KakaoPay kakaoPay;

    @Transactional
    public PayRequest placeOrder(List<OrderItem> orderItems) {
        log.info("order service: {}", Thread.currentThread().getId());

        checkCanBuyProduct(orderItems);

        Order order = Order.builder()
                .orderItems(orderItems)
                .build();
        orderRepository.save(order);

        eventPublisher.publishEvent(new OrderEvent(this, order.getId()));

        PayReady payReady = kakaoPay.prepare(order);

        return PayRequest.builder()
                .payReady(payReady)
                .order(order)
                .build();
    }

    private void checkCanBuyProduct(List<OrderItem> orderItems) {
        for (OrderItem orderItem : orderItems) {
            Product product = productService.getProductDetail(orderItem.getId().toString());
            orderItem.canBuy(product.getStock());
        }
    }
}
