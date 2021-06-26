package com.zkdlu.domain.order;

import com.zkdlu.domain.payment.KakaoPay;
import com.zkdlu.domain.payment.remote.PayReady;
import com.zkdlu.domain.product.Product;
import com.zkdlu.domain.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
            Product product = productService.getProductDetail(orderItem.getId());
            orderItem.canBuy(product.getStock());
        }
    }
}

