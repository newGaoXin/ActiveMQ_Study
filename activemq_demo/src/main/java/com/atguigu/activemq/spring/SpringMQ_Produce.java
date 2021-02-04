package com.atguigu.activemq.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;

@Service
public class SpringMQ_Produce {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        SpringMQ_Produce springMQ_produce = (SpringMQ_Produce) applicationContext.getBean("springMQ_Produce");

//        springMQ_produce.jmsTemplate.send(new MessageCreator() {
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                TextMessage textMessage = session.createTextMessage("*******Spring和MQ的整合case11111.....");
//                return textMessage;
//            }
//        });

        springMQ_produce.jmsTemplate.send(session -> {
            TextMessage textMessage = session.createTextMessage("*******Spring和MQ的整合case11111.....");
            return textMessage;
        });

        System.out.println("send task over");
    }
}
