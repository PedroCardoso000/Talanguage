package com.talalanguage.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TalaLanguageApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TalaLanguageApiApplication.class, args);
        System.out.println("===============================================");
        System.out.println("       Application started successfully!       ");
        System.out.println("===============================================");
    }
}
