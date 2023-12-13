package com.springboot.demo.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @JmsListener(destination = "BatchQueue")
    public void reveive(String message){
        System.out.println("收Queue message:"+message);
    }
}
