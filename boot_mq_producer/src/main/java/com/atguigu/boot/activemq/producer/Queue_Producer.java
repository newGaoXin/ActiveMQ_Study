package com.atguigu.boot.activemq.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.UUID;

@Component
public class Queue_Producer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    public void producerMsg() {
        jmsMessagingTemplate.convertAndSend(queue, "******: " + UUID.randomUUID().toString().substring(0, 6));
    }

    @Scheduled(fixedDelay = 3000L)
    public void producerMsg_scheduled() {
        jmsMessagingTemplate.convertAndSend(queue, "******producerMsg_scheduled: " + UUID.randomUUID().toString().substring(0, 6));
        System.out.println("producerMsg_scheduled send ok");
    }
}
