package com.example.bhandarpoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BhandarPooApplication extends SpringBootServletInitializer {

    // Entry point when deployed as a WAR to an external server (e.g. Tomcat)
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BhandarPooApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BhandarPooApplication.class, args);
    }
}
