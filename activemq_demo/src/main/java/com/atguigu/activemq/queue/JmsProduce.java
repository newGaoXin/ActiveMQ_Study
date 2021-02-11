package com.atguigu.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsProduce {

//    public static final String DEFAULT_BROKER_BIND_URL = "nio://120.27.111.97:61618";
public static final String DEFAULT_BROKER_BIND_URL = "failover:(tcp://120.27.111.97:61616,tcp://120.27.111.97:61617,tcp://120.27.111.97:61618)";
    public static final String QUEUE_NAME = "test_cluster";

    public static void main(String[] args) throws JMSException {

        // 1.创建连接工厂，按照给定的url地址，采用默认用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(DEFAULT_BROKER_BIND_URL);
        // 2.通过连接工厂，获得连接connection并访问启动
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        // 3.创建会话session
        // 两个参数，第一个叫事务/第二个叫签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4.创建目的地（具体是队列还是主题topic）
        Queue queue = session.createQueue(QUEUE_NAME);
        // 5.创建消息的生产者
        MessageProducer messageProducer = session.createProducer(queue);
        // 持久化设置
        messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);   // 非持久化
//        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);   // 持久化
        // 6.通过使用messageProducer生产3条消息发送到MQ的队列里面
        for (int i = 1; i <= 3; i++) {
            // 7 创建消息，好比学生按照老师的要求写好的面试题消息
            TextMessage textMessage = session.createTextMessage("MessageListener----" + i); // 理解为一个字符串
            textMessage.setStringProperty("c01","vip");
            // 8.通过messageProducer发送给mq
            messageProducer.send(textMessage);

            // MapMessage
            MapMessage mapMessage = session.createMapMessage();
            mapMessage.setString("k1","MapMessage v1");
            messageProducer.send(mapMessage);
        }
        // 9.关闭资源
        messageProducer.close();
        session.close();
        connection.close();

        System.out.println("********消息发送到MQ完成********");
    }
}
