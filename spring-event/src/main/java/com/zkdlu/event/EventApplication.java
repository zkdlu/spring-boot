package com.zkdlu.event;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(EventApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
