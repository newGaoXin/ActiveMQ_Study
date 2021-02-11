package com.atguigu.boot.activemq;

import com.atguigu.boot.activemq.producer.Queue_Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@SpringBootTest(classes = MainApp_Producer.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestActiveMQ {

    @Resource
    private Queue_Producer queue_producer;

    @Test
    public void testSend(){
        queue_producer.producerMsg();
    }
}
