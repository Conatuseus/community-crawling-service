package com.conatuseus.communityservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CommunityCrawlingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunityCrawlingServiceApplication.class, args);
    }

}
