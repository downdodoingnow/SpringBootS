package com.example.demo;

import com.example.demo.rabbitMQ.Sender;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.security.RunAs;

@SpringBootTest
class SpringBootTApplicationTests {
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
        System.out.println("testSend");
        sender.send();
    }
}
