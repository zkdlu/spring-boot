package com.zkdlu.feign.source;

import feign.FeignException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FeignExceptionHandler {
    @ExceptionHandler(FeignException.InternalServerError.class)
    public Demo handle(FeignException e) {
        e.printStackTrace();

        return new Demo("exception");
    }
}
