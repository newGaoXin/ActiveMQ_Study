package com.atguigu.boot.activemq.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class Queue_Consumer {

    @JmsListener(destination = "${myQueue}")
    private void receiver(TextMessage textMessage) throws JMSException {
        System.out.println("******消费者接收到的消息：" + textMessage.getText());
    }
}
