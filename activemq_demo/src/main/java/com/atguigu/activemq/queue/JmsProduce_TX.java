package com.atguigu.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsProduce_TX {

    public static final String DEFAULT_BROKER_BIND_URL = "tcp://120.27.111.97:61616";
    public static final String QUEUE_NAME = "queue01";

    public static void main(String[] args) throws JMSException {
        // 1.创建工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(DEFAULT_BROKER_BIND_URL);
        // 2.创建连接、启动
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        // 3.创建session
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        // 创建queque、生产者
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageProducer messageProducer = session.createProducer(queue);
        for (int i = 1; i <= 3; i++) {
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText("生产者发送消息：" + i);
            messageProducer.send(textMessage);
        }
        messageProducer.close();
        session.commit();
        session.close();
        connection.close();

        System.out.println("********消息发送到MQ完成********");
    }
}
