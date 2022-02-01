package com.zkdlu.reactive.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

@RestController
public class DemoController {
    @GetMapping
    Flux<String> hello() {
        return Flux.just("Hello", " ", "World");
    }

    @GetMapping("/stream")
    Flux<Map<String, Integer>> stream() {
        Stream<Integer> stream = Stream.iterate(0, i -> i + 1);
        return Flux.fromStream(stream)
                .map(i -> Collections.singletonMap("value", i));
    }

    @PostMapping("/echo")
    Mono<Demo> echo(@RequestBody Mono<Demo> body) {
        return body;
    }

    @NoArgsConstructor
    @Getter
    private static class Demo {
        private String id;
    }
}
