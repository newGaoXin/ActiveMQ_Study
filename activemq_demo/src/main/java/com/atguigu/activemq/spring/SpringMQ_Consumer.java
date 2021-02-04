package com.atguigu.activemq.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class SpringMQ_Consumer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        SpringMQ_Consumer springMQ_consumer = (SpringMQ_Consumer) applicationContext.getBean("springMQ_Consumer");

        String result = (String) springMQ_consumer.jmsTemplate.receiveAndConvert();

        System.out.println("****消费者接收到消息：" + result);
    }
}
