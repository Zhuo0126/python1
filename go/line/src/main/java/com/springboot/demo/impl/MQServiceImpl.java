package com.springboot.demo.impl;

import com.springboot.demo.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;


@Service
public class MQServiceImpl implements MessageService {

    @Autowired(required = false)
    private JmsTemplate messagingTemplate;

    @Override
    public void sendMessage(String destination, String message) {
        messagingTemplate.convertAndSend(destination, message);
        System.out.println("已成功送"+destination+":"+message);
    }

}
