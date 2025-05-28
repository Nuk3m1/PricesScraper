package com.example.pricesscraper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.pricesscraper.mapper")
public class PricesScraperApplication {

    public static void main(String[] args) {
        SpringApplication.run(PricesScraperApplication.class, args);
    }

}
