package com.zkdlu;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
public class DemoService {
    private static final Logger log = LoggerFactory.getLogger(DemoService.class);
    private static final String TEST_CIRCUIT_BREAKER = "testCircuitBreaker";

    @CircuitBreaker(name = TEST_CIRCUIT_BREAKER, fallbackMethod = "helloFallback")
    public String success() {
        return "success";
    }

    @CircuitBreaker(name = TEST_CIRCUIT_BREAKER, fallbackMethod = "helloFallback")
    public String fail() {
        throw new IllegalStateException("fail");
    }

    @CircuitBreaker(name = TEST_CIRCUIT_BREAKER, fallbackMethod = "retryFallback")
    @Retry(name = TEST_CIRCUIT_BREAKER)
    public Mono<String> retry() {
        log.info("retry");
        WebClient webClient = WebClient
                .builder()
                .baseUrl("http://localhost:9000")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:9000"))
                .build();

        webClient.get();

        return webClient
                .method(HttpMethod.GET)
                .uri("/")
                .retrieve()
                .bodyToMono(String.class);
    }

    public String helloFallback(Throwable t) {
        return "fallback invoked!: " + t.getClass();
    }

    public Mono<String> retryFallback(Throwable t) {
        return Mono.just("fallback invoked!: " + t.getClass());
    }
}
