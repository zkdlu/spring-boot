package com.zkdlu.api.order;

import com.zkdlu.domain.order.OrderItem;
import com.zkdlu.order.service.OrderService;
import com.zkdlu.order.service.PayRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class OrderApi {
    private final OrderService orderService;

    @PostMapping("/order")
    public PayRequest order(@RequestBody Cart cart, HttpSession session) {
        List<OrderItem> orderItems = cart.getItems()
                .stream()
                .map(CartItem::toOrderItem)
                .collect(Collectors.toList());

        PayRequest payRequest = orderService.placeOrder(orderItems);


        session.setAttribute("payReady", payRequest.getPayReady());

        return payRequest;
    }
}
