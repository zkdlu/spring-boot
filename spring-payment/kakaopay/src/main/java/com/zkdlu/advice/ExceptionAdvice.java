package com.zkdlu.advice;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(IllegalStateException.class)
    public String handle(HttpServletRequest request, Exception e) {
        return "재고없음";
    }
}
