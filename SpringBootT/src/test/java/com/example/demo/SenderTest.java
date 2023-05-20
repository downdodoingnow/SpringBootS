package com.example.demo;

import com.example.demo.rabbitMQ.Sender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SpringBootApplication.class)
public class SenderTest {
    @Autowired
    Sender sender;

    public void before(){
        System.out.println("before send");
    }

    public void after(){
        System.out.println("after send");
    }

    @Test
    public void testSend(){
        sender.send();
    }
}
