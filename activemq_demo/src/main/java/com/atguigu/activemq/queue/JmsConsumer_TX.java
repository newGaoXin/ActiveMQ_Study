package com.atguigu.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsConsumer_TX {
    public static final String DEFAULT_BROKER_BIND_URL = "tcp://120.27.111.97:61616";
    public static final String QUEUE_NAME = "queue01";

    public static void main(String[] args) throws JMSException {
        // 1.创建工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(DEFAULT_BROKER_BIND_URL);
        // 2.创建连接
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        // 3.创建session
        Session session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);
        // 4.创建queue、消费者
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageConsumer messageConsumer = session.createConsumer(queue);
        // 5.消费
        while (true){
            TextMessage textMessage =(TextMessage) messageConsumer.receive(4000L);
            if (textMessage != null) {
                System.out.println(textMessage.getText());
                textMessage.acknowledge();
            } else {
                break;
            }
        }
        messageConsumer.close();
        session.commit();
        session.close();
        connection.close();

    }
}
