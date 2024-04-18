package com.example.api_202404;

import com.example.api_202404.services.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
@SpringBootApplication
public class Api202404Application {
    private final SchoolService schoolService;

    public static void main(String[] args) {
        SpringApplication.run(Api202404Application.class, args);
    }

}
