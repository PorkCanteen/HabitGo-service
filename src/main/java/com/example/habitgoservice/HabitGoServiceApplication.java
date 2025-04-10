package com.example.habitgoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableScheduling  // 添加这个注解
public class HabitGoServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(HabitGoServiceApplication.class, args);
    }
}
