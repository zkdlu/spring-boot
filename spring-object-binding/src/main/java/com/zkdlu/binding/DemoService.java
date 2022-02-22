package com.zkdlu.binding;

public interface DemoService {
    BodyData.BodyDataResponse mapFrom(BodyData.BodyDataRequest request);

    void mapFrom(ModelAttributeData.ModelAttributeRequest model);
}
