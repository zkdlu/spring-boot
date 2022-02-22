package com.zkdlu.binding;

import com.zkdlu.binding.BodyData.BodyDataRequest;
import com.zkdlu.binding.BodyData.BodyDataResponse;

public class SpyDemoService implements DemoService {
    public BodyDataRequest mapFrom_argument;

    @Override
    public BodyDataResponse mapFrom(BodyDataRequest request) {
        mapFrom_argument = request;
        return request.toResponse();
    }
}