package com.zkdlu.binding;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class DemoApiTest {

    private MockMvc mockMvc;
    private SpyDemoService spyDemoService;

    @BeforeEach
    void setUp() {
        spyDemoService = new SpyDemoService();
        mockMvc = MockMvcBuilders.standaloneSetup(new DemoApi(spyDemoService)).build();
    }

    @Nested
    public class PostRequestBody {
        @Test
        void postWithRequestBody_passesRequestToService() throws Exception {
            String json = "{\"id\":\"id1\",\"name\":\"name1\"}";
            mockMvc.perform(post("/requestBody")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json));

            assertThat(spyDemoService.mapFrom_argument.getId()).isEqualTo("id1");
            assertThat(spyDemoService.mapFrom_argument.getName()).isEqualTo("name1");
        }

        @Test
        void postWithRequestBody_returnsRequestData() throws Exception {
            String json = "{\"id\":\"id1\",\"name\":\"name1\"}";
            mockMvc.perform(post("/requestBody")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(jsonPath("$.id", equalTo("id1")))
                    .andExpect(jsonPath("$.name", equalTo("name1")));
        }
    }
}