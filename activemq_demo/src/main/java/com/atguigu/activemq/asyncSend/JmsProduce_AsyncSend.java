package com.atguigu.activemq.asyncSend;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;

import javax.jms.*;
import java.util.UUID;

public class JmsProduce_AsyncSend {

    public static final String DEFAULT_BROKER_BIND_URL = "tcp://120.27.111.97:61616";
    public static final String QUEUE_NAME = "queue_asyncSend";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(DEFAULT_BROKER_BIND_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        ActiveMQMessageProducer activeMQMessageProducer = (ActiveMQMessageProducer) session.createProducer(queue);
        for (int i = 1; i <= 3; i++) {
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText("生产者发送消息:" + i);
            textMessage.setJMSMessageID(UUID.randomUUID().toString() + "----order");
            String jmsMessageID = textMessage.getJMSMessageID();
            activeMQMessageProducer.send(textMessage, new AsyncCallback() {
                @Override
                public void onSuccess() {
                    System.out.println("success");
                }

                @Override
                public void onException(JMSException exception) {
                    System.out.println("error");
                }
            });
        }
        activeMQMessageProducer.close();
        session.close();
        connection.close();

        System.out.println("********消息发送到MQ完成********");
    }
}
