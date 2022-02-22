package com.zkdlu.oauthapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication
public class OauthappApplication {
    public static void main(String[] args) {
        SpringApplication.run(OauthappApplication.class, args);
    }
}
