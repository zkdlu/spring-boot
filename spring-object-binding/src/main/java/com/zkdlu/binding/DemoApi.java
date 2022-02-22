package com.zkdlu.binding;

import com.zkdlu.binding.BodyData.BodyDataRequest;
import com.zkdlu.binding.BodyData.BodyDataResponse;
import com.zkdlu.binding.ModelAttributeData.ModelAttributeRequest;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoApi {
    private final DemoService demoService;

    public DemoApi(DemoService demoService) {
        this.demoService = demoService;
    }

    @PostMapping("/requestBody")
    public BodyDataResponse postWithRequestBody(@RequestBody BodyDataRequest body) {
        return demoService.mapFrom(body);
    }

    @PostMapping("/modelattribute")
    public void postWithModelAttribute(@ModelAttribute ModelAttributeRequest model) {
        demoService.mapFrom(model);
    }

}
