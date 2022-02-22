package com.zkdlu.binding;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class ModelAttributeData {
    @Getter
    @AllArgsConstructor
    public class ModelAttributeRequest {
        private String id;
        private String name;
    }
}
