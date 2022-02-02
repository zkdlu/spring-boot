package com.zkdlu.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin("*")
@Controller
public class WebController {
    @GetMapping
    public String index() {
        return "index.html";
    }

    @GetMapping("/order/{productId}")
    public String order(Model model, @PathVariable String productId) {
        model.addAttribute("productId", productId);

        return "order.html";
    }
}
