package com.zkdlu.demo.service.order;

import com.zkdlu.demo.domain.order.Order;
import com.zkdlu.demo.domain.order.OrderItem;
import com.zkdlu.demo.domain.order.OrderRepository;
import com.zkdlu.demo.domain.shop.Product;
import com.zkdlu.demo.domain.shop.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void placeOrder(OrderRequest orderRequest) {
        Product product = productRepository.findById(orderRequest.getProductId()).orElseThrow(IllegalStateException::new);
        Order order = new Order(orderRequest.getOrderId(),
                new OrderItem(product));

        order.place();
        orderRepository.save(order);
    }

    @Transactional
    public void payOrder(long id) {
        Order order = orderRepository.findById(id).orElseThrow(IllegalStateException::new);;
        order.payed();

        order.delivery();
    }
}
