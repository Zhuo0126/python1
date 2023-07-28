package com.springboot.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
@MapperScan("com.springboot.demo.dao.UserMapper")
public class Application {

	public static void main(String[] args) {
//		SpringApplication app =new SpringApplication(Application.class);
//		app.setDefaultProperties(Collections.singletonMap("server=port","3000"));
//		app.run(args);
		SpringApplication.run(Application.class,args);
	}
}
