package com.springboot.demo;

import com.springboot.demo.impl.MQServiceImpl;
import com.springboot.demo.util.SpringBeanFactoryUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
//@AutoConfigureMockMvc
@SpringBootTest
public class SpringBootJDBCTest {
    @Autowired(required = false)
    private MQServiceImpl mqService;

    @Test
    public void testJMS() throws Exception {
        //測試Active MQ收送
        mqService.sendMessage("TestQueue","20231220");
    }
}