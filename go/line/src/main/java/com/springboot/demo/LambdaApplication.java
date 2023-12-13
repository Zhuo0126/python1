package com.springboot.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

//@SpringBootApplication
public class LambdaApplication {
	public static void main(String[] args) {
		List<String> strList = Arrays.asList("A","B","C");

		strList.forEach(s -> System.out.println(s));
	}
}
