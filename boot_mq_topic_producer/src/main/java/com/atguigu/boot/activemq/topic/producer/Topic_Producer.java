package com.atguigu.boot.activemq.topic.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Topic;
import java.util.UUID;

@Component
public class Topic_Producer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Topic topic;

    @Scheduled(fixedDelay = 3000L)
    public void producerTopic_scheduled() {
        jmsMessagingTemplate.convertAndSend(topic, "****: " + UUID.randomUUID().toString().substring(0, 6));
    }
}
