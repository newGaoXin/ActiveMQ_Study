package com.atguigu.boot.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MainApp_Producer {

    public static void main(String[] args) {
        SpringApplication.run(MainApp_Producer.class,args);
    }
}
