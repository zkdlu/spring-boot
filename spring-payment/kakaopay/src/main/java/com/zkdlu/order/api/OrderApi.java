package com.zkdlu.order.api;

import com.zkdlu.order.service.OrderService;
import com.zkdlu.order.service.PayRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class OrderApi {
    private final OrderService orderService;

    @PostMapping("/order")
    public PayRequest order(@RequestBody Cart cart, HttpSession session) {
        var payRequest = orderService.placeOrder(cart.getItems()
                .stream()
                .map(CartItem::toOrderItem)
                .collect(Collectors.toList()));

        session.setAttribute("payReady", payRequest.getPayReady());

        return payRequest;
    }
}
