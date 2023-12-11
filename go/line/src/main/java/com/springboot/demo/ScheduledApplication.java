package com.springboot.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class ScheduledApplication{

	public static void main(String[] args) {
		// 創建一個ScheduledExecutorService實例
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);

		// 同時安排十個不同的任務
		for (int i = 1; i <= 3; i++) {
			int taskId = i;  //給cliendid
			Runnable task = () -> {
				//寫收queue方法
				if(taskId == 2){
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
				System.out.println("Task " + taskId + " executed at " + System.currentTimeMillis());
			};

			executorService.scheduleWithFixedDelay(task, 0, 3, TimeUnit.SECONDS);
		}

	}
}
