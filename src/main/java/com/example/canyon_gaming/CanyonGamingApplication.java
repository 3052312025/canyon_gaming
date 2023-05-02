package com.example.canyon_gaming;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.canyon_gaming.mapper")
public class CanyonGamingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CanyonGamingApplication.class, args);
    }

}
