package com.zkdlu.demo.service.order;

import com.zkdlu.demo.domain.order.Order;
import com.zkdlu.demo.domain.order.OrderItem;
import com.zkdlu.demo.domain.shop.Product;
import com.zkdlu.demo.repository.OrderRepository;
import com.zkdlu.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    @Transactional
    public void placeOrder(OrderRequest orderRequest) {
        Product product = productRepository.findById(orderRequest.getProductId());
        Order order = new Order(orderRequest.getOrderId(),
                new OrderItem(product));

        order.place();
        orderRepository.save(order);
    }

    public void payOrder(long id) {
        Order order = orderRepository.findById(id);
        order.payed();

        order.delivery();
    }
}
