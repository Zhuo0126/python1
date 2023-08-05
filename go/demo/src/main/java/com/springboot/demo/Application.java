package com.springboot.demo;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Collections;

@SpringBootApplication
//@Mapper
@MapperScan("com.springboot.demo.mapper.*")
//@ComponentScan(basePackages = {"com.springboot.demo.model","com.springboot.demo.controller","com.springboot.demo.service","com.springboot.demo.impl","com.springboot.demo.mapper"})
public class Application {

	public static void main(String[] args) {
//		SpringApplication app =new SpringApplication(Application.class);
//		app.setDefaultProperties(Collections.singletonMap("server=port","3000"));
//		app.run(args);
		SpringApplication.run(Application.class,args);
	}
}
