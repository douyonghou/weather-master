package com.bs.weather;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
@MapperScan("com.bs.weather.mapper")
public class GeiasApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeiasApplication.class, args);
    }

}
