package com.example.demo.rabbitMQ;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Sender {
    public static final String QUEUE_NAME = "chatbot";
    @Autowired
    public AmqpTemplate amqpTemplate;

    public void send(){
        String context = "hello " + new Date();
        System.out.println("Sender:" + context);
        amqpTemplate.convertAndSend(QUEUE_NAME,context);
    }
}
