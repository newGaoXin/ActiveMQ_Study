package com.atguigu.boot.activemq.topic.config;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.jms.Topic;

@Component
public class ConfigBean {

    @Value("${myTopic}")
    private String myTopic;

    @Bean
    private Topic topic(){
        return new ActiveMQTopic(myTopic);
    }
}
