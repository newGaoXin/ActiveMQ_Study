package com.atguigu.boot.activemq.topic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MainApp_Topic_Producer {

    public static void main(String[] args) {
        SpringApplication.run(MainApp_Topic_Producer.class, args);
    }
}
