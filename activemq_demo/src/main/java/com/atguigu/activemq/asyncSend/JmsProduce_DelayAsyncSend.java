package com.atguigu.activemq.asyncSend;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.ScheduledMessage;

import javax.jms.*;

public class JmsProduce_DelayAsyncSend {

    public static final String DEFAULT_BROKER_BIND_URL = "tcp://120.27.111.97:61616";
    public static final String QUEUE_NAME = "queue_asyncSend";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(DEFAULT_BROKER_BIND_URL);
        activeMQConnectionFactory.setUseAsyncSend(true);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        ActiveMQMessageProducer activeMQMessageProducer = (ActiveMQMessageProducer) session.createProducer(queue);

        long delay = 3 * 1000L;
        long period = 4 * 1000L;
        int repeat = 5;

        for (int i = 1; i <= 3; i++) {
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText("生产者发送的消息:" + i);
            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delay);
            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, period);
            textMessage.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, repeat);
            activeMQMessageProducer.send(textMessage);
        }

        activeMQMessageProducer.close();
        session.close();
        connection.close();

        System.out.println("MQ生产者完成生产！！！");
    }
}
