package com.atguigu.boot.activemq.topic.comsuer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class Topic_Consumer {

    @JmsListener(destination = "${myTopic}")
    public void receiver(TextMessage textMessage) throws JMSException {
        System.out.println("消费者接收到的消息：" + textMessage.getText());
    }
}
