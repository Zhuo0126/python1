package com.springboot.demo;


import jakarta.jms.Queue;
import jakarta.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;


@SpringBootApplication
//@EnableJms
public class RunApplication  {

	public static void main(String[] args) {
		try {
			System.out.println("Hello world");
//			ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
//			Connection connection= activeMQConnectionFactory.createConnection();
//			connection.start();
//			Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//			Queue queue=session.createQueue("TestQueue");
//			SpringApplication.run(RunApplication.class, args);
		}catch (Exception e){
			System.out.println(e);
		}
	}
}
