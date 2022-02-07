package com.zkdlu.feign.source;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class EntryApi {
    private final ObjectMapper objectMapper;
    private final DemoClient demoClient;

    @GetMapping("/correct")
    public String correct() throws JsonProcessingException {
        var demo = demoClient.demo1();
        return objectMapper.writeValueAsString(demo);
    }

    @GetMapping("/incorrect")
    public String incorrect() throws JsonProcessingException {
        var demo = demoClient.demo2();
        return objectMapper.writeValueAsString(demo);
    }

    @GetMapping("/exception")
    public String exception() throws JsonProcessingException {
        var demo = demoClient.demo3();
        return objectMapper.writeValueAsString(demo);
    }

    @GetMapping("/fallback")
    public String circuitOpen() throws JsonProcessingException {
        var demo = demoClient.demo4();

        return objectMapper.writeValueAsString(demo);
    }
}
