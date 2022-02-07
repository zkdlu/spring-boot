package com.zkdlu.feign.source;

import org.springframework.stereotype.Component;

@Component
public class DemoClientFallback implements DemoClient {
    @Override
    public Demo demo1() {
        return new Demo("fallback1");
    }

    @Override
    public Demo demo2() {
        return new Demo("fallback2");
    }

    @Override
    public Demo demo3() {
        return new Demo("fallback3");
    }

    @Override
    public Demo demo4() {
        return new Demo("fallback4");
    }
}
