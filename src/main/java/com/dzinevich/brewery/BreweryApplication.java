package com.dzinevich.brewery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BreweryApplication {

    public static void main(String[] args) {
        SpringApplication.run(BreweryApplication.class, args);
    }

}
