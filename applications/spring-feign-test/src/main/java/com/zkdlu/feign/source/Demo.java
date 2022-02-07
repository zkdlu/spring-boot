package com.zkdlu.feign.source;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Demo {
    private String content;

    public Demo(String content) {
        this.content = content;
    }
}
