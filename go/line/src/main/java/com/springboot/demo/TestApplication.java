package com.springboot.demo;

import com.springboot.demo.model.Login;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class TestApplication extends TimerTask {

	public void run(){
		System.out.println("Timer task executed!");
	}
	public static void main(String[] args) {
		Timer timer = new Timer();

		timer.schedule(new TestApplication(),2000,2000);
	}
}
