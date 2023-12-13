package com.springboot.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//@SpringBootApplication
public class TaskApplication {

	public static void main(String[] args) {
		int numberOfTransactions = 1000;
		ExecutorService executor = Executors.newFixedThreadPool(10); //10個線程

		for (int i = 0; i < numberOfTransactions; i++) {
			Runnable transactionTask = new TransactionTask(i);
			executor.execute(transactionTask);
		}

		executor.shutdown();
	}
	static class TransactionTask implements Runnable {
		private int transactionId;

		public TransactionTask(int transactionId) {
			this.transactionId = transactionId;
		}

		@Override
		public void run() {
			System.out.println(transactionId + " on thread: " + Thread.currentThread().getName());
		}
	}
}
