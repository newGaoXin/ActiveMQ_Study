package com.atguigu.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class JmsConsumer {

//    public static final String DEFAULT_BROKER_BIND_URL = "nio://120.27.111.97:61618";
    public static final String DEFAULT_BROKER_BIND_URL = "tcp://120.27.234.97:61616";
    public static final String QUEUE_NAME = "queue_asyncSend";

    public static void main(String[] args) throws JMSException, IOException {
        System.out.println("我是2号消费者");
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
        // 创建消息的消费者
        MessageConsumer messageConsumer = session.createConsumer(queue);

        /*
        同步阻塞方式(receive())
        订阅者或接收者调用MessageConsumer的receive()方法来接收消息，receive方法在能够接收到消息之前（或超时之前）将一直阻塞
        while (true) {
            TextMessage textMessage = (TextMessage) messageConsumer.receive(4000L);
            if (textMessage != null) {
                System.out.println("消费者接收到消息： " + textMessage.getText());
            } else {
                break;
            }
        }
        messageConsumer.close();
        session.close();
        connection.close();

         */

        // 通过监听的方法来消费消息
        /*
            异步非阻塞方式（监听器onMessage()）
            订阅者或接收者通过MessageConsumer的setMessageListener(MessageListener listener)注册一个消息监听器
            当消息到达之后，系统自动调用监听器Messagelistener的onMessage(Message message)方法
         */
        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if (message != null && message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("消费者接收到消息： " + textMessage.getText());
                        System.out.println("消费者接收到消息属性：" + textMessage.getStringProperty("c01"));
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }

                if (message != null && message instanceof MapMessage) {
                    MapMessage mapMessage = (MapMessage) message;
                    String k1 = null;
                    try {
                        k1 = mapMessage.getString("k1");
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                    System.out.println("消费者接收到消息：" + k1);
                }
            }
        });
        System.in.read();   //  保证控制台不灭
        messageConsumer.close();
        session.close();
        connection.close();

        /*
            1.先生产，只启动1号消费者。问题：1号消费者能消费消息吗？      Y

            2.先生产，先启动1号消费者再启动2号消费者，问题：2号消费者还能消费吗？
            2.1     1号消费者可以消费吗？     Y
            2.2     2号消费者可以消费吗？     N

            3.先启动2个消费者，再生产6条消息，请问，消费情况如何？
            3.1     2个消费者都有6条？          N
            3.2     先到先得，6条全都给一个？   N
            3.3     一人一半                    Y
         */
    }
}
