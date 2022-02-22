package com.zkdlu.binding;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ModelAttributeData {
    @Getter
    @Setter
    public static class ModelAttributeRequest {
        private String id;
        private String name;
        private List<ModelAttributeItem> list;
    }

    @Setter
    @Getter
    public static class ModelAttributeItem {
        private String id;
        private String name;
    }
}
