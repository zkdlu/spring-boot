package com.zkdlu.scheduled;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ScheduledApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(ScheduledApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
