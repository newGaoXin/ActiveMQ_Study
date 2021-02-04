package com.atguigu.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsProduce_Topic {

    public static final String DEFAULT_BROKER_BIND_URL = "tcp://120.27.111.97:61616";
    public static final String TOPIC_NAME = "topic01";

    public static void main(String[] args) throws JMSException {
        // 1.创建工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(DEFAULT_BROKER_BIND_URL);
        // 2.创建连接、启动
        Connection connection = activeMQConnectionFactory.createConnection();

        // 3.创建 session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4.创建生产者 、topic
        Topic topic = session.createTopic(TOPIC_NAME);
        MessageProducer messageProducer = session.createProducer(topic);
        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);   // 持久化
        connection.start();
        // 5.发送消息
        for (int i = 1; i <= 3; i++) {
            TextMessage textMessage = session.createTextMessage("发送消息给 topic01----" + i);
            messageProducer.send(textMessage);
        }
        messageProducer.close();
        session.close();
        connection.close();

        System.out.println("********消息发送到topic完成********");
    }
}
