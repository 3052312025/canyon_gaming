package com.example.canyon_gaming;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.example.canyon_gaming.mapper")
public class CanyonGamingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CanyonGamingApplication.class, args);
    }

}
