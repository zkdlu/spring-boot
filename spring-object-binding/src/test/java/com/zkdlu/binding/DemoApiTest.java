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

            assertThat(spyDemoService.mapFrom_argumentRequestBody.getId()).isEqualTo("id1");
            assertThat(spyDemoService.mapFrom_argumentRequestBody.getName()).isEqualTo("name1");
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

    @Nested
    public class PostModelAttribute {
        @Test
        void postWithModelAttribute_passesRequestToService() throws Exception {
            mockMvc.perform(post("/modelattribute")
                    .param("id", "id1")
                    .param("name", "name1")
                    .param("list[0].id", "list-id")
                    .param("list[0].name", "list-name"));

            assertThat(spyDemoService.mapFrom_argumentModelAttribute.getId()).isEqualTo("id1");
            assertThat(spyDemoService.mapFrom_argumentModelAttribute.getName()).isEqualTo("name1");
            assertThat(spyDemoService.mapFrom_argumentModelAttribute.getList().get(0).getId()).isEqualTo("list-id");
            assertThat(spyDemoService.mapFrom_argumentModelAttribute.getList().get(0).getName()).isEqualTo("list-name");
        }
    }
}