package com.example.demo.rabbitMQ;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String QUEUE_NAME = "chatbot";
    @Bean
    public Queue helloQueue(){
        return new Queue(QUEUE_NAME);
    }
}
