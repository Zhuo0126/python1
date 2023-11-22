package com.springboot.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class TimerApplication extends TimerTask {

	public void run(){
		System.out.println("Timer task executed!");
	}
	public static void main(String[] args) {
		Timer timer = new Timer();

		timer.schedule(new TimerApplication(),2000,2000);
	}
}
