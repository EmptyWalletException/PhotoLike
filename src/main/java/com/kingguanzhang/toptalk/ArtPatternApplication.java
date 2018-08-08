package com.kingguanzhang.toptalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ArtPatternApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArtPatternApplication.class, args);
    }
}
