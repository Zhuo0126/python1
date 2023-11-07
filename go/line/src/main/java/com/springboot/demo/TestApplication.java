package com.springboot.demo;

import com.springboot.demo.model.Login;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		Login login =new Login();
		login.setID(1);
		login.setRESOURCENAME("test");
		System.out.println("ID="+login.getID()+", ResourceName="+login.getRESOURCENAME());
	}
}
