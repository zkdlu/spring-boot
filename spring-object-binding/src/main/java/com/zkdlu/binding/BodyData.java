package com.zkdlu.binding;

import lombok.Getter;

public class BodyData {
    @Getter
    public static class BodyDataRequest {
        private String id;
        private String name;

        public BodyDataResponse toResponse() {
            return new BodyDataResponse(id, name);
        }
    }

    @Getter
    public static class BodyDataResponse {
        private String id;
        private String name;

        public BodyDataResponse(String id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
