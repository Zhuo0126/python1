package com.springboot.demo.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public interface MessageService {
    void sendMessage(String des,String id);
}