package com.zkdlu;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempController {
    @GetMapping
    public String temp() {
        return "temp";
    }
}
