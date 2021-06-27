package com.zkdlu;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
public class DemoService {
    private static final String TEST_CIRCUIT_BREAKER = "testCircuitBreaker";

    @CircuitBreaker(name = TEST_CIRCUIT_BREAKER, fallbackMethod = "helloFallback")
    public String success() {
        return "success";
    }

    @CircuitBreaker(name = TEST_CIRCUIT_BREAKER, fallbackMethod = "helloFallback")
    public String fail() {
        throw new IllegalStateException("fail");
    }

    @CircuitBreaker(name = TEST_CIRCUIT_BREAKER, fallbackMethod = "helloFallback")
    @Retry(name = TEST_CIRCUIT_BREAKER)
    public String retry() {
        throw new IllegalStateException("fail");
    }

    private String helloFallback(Throwable t) {
        return "fallback invoked!: " + t.getClass();
    }

    @Bulkhead(name = TEST_CIRCUIT_BREAKER)
    @CircuitBreaker(name = TEST_CIRCUIT_BREAKER, fallbackMethod = "fallback")
    public Mono<String> getFailHello() {
        return Mono.error(new IllegalStateException("CustomException"));
    }

    @RateLimiter(name = TEST_CIRCUIT_BREAKER)
    @Bulkhead(name = TEST_CIRCUIT_BREAKER)
    @CircuitBreaker(name = TEST_CIRCUIT_BREAKER, fallbackMethod = "fallback")
    public Mono<String> getSuccessHello() {
        return Mono.just("Hello");
    }

    @TimeLimiter(name = TEST_CIRCUIT_BREAKER)
    @Retry(name = TEST_CIRCUIT_BREAKER)
    @CircuitBreaker(name = TEST_CIRCUIT_BREAKER, fallbackMethod = "fallback")
    public Mono<String> getDataFromRemoteServer() {
        WebClient webClient = WebClient
                .builder()
                .baseUrl("http://localhost:9090")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:9090"))
                .build();

        webClient.get();

        return webClient
                .method(HttpMethod.GET)
                .uri("/")
                .retrieve()
                .bodyToMono(String.class);
    }

    public String fallback(Throwable t) {
        return "fallback";
    }
}
