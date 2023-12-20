package com.springboot.demo.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @JmsListener(destination = "BatchQueue")
    public void reveiveBatch(String message){
        System.out.println("收Batch Queue message:"+message);
    }

    @JmsListener(destination = "TestQueue")
    public void reveiveTest(String message){
        System.out.println("收Test Queue message:"+message);
    }
}
