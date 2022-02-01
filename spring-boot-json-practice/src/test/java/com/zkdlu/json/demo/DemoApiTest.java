package com.zkdlu.json.demo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DemoApiTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        DemoApi demoApi = new DemoApi();
        ObjectMapper objectMapper = new ObjectMapper()
                .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        mockMvc = MockMvcBuilders.standaloneSetup(demoApi)
                .setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                .build();
    }

    @Test
    void getDemo_returns_okHttpStatus() throws Exception {
        mockMvc.perform(get("/demo"))
                .andExpect(status().isOk());
    }

    @Test
    void getDemo_returns_a_singleDemo() throws Exception {
        mockMvc.perform(get("/demo"))
                .andDo(print())
                .andExpect(jsonPath("$.intValue", equalTo(1)))
                .andExpect(jsonPath("$.stringValue", equalTo("str")))
                .andExpect(jsonPath("$.floatValue", equalTo(1.0)))
                .andExpect(jsonPath("$.booleanValue", equalTo(true)));
    }
}