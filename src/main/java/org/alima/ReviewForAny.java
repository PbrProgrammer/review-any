package org.alima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ReviewForAny {
    public static void main(String[] args) {
        SpringApplication.run(ReviewForAny.class, args);
    }
}