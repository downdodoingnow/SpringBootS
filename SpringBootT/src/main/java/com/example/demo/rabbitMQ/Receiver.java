package com.example.demo.rabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "chatbot")
public class Receiver {
    @RabbitHandler
    public void process(String message){
        System.out.println("Receiver:" + message);
    }
}
