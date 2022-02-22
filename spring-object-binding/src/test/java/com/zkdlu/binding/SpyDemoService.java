package com.zkdlu.binding;

import com.zkdlu.binding.BodyData.BodyDataRequest;
import com.zkdlu.binding.BodyData.BodyDataResponse;
import com.zkdlu.binding.ModelAttributeData.ModelAttributeRequest;

public class SpyDemoService implements DemoService {
    public BodyDataRequest mapFrom_argumentRequestBody;
    public ModelAttributeRequest mapFrom_argumentModelAttribute;

    @Override
    public BodyDataResponse mapFrom(BodyDataRequest request) {
        mapFrom_argumentRequestBody = request;
        return request.toResponse();
    }

    @Override
    public void mapFrom(ModelAttributeRequest model) {
        mapFrom_argumentModelAttribute = model;
    }
}