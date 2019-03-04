package com.b2w.starwar;

import com.b2w.starwar.infrastructure.StarwarProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StarwarProperty.class)
public class StarwarApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarwarApplication.class, args);
    }

}
