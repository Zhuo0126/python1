package com.springboot.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class FISCApplication {

    public static void main(String[] args) {
        String filePath = "D:/TXTT/開發log/2500.txt";
        String id = "RESTFulIn,CBSIn,CBSAscii,CBSOut,AAOut";

		Scanner scanner = new Scanner(System.in);

		while (true) {

			System.out.print("請輸入-EJ(結束請輸入:exit)：");
			String EJ = scanner.nextLine();

			if(EJ.equals("exit")){
				break;
			}

			try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
				String line;
				while ((line = br.readLine()) != null) {
					// 在這裡處理每一行的內容
					String[] t = line.split("\\|");

					// 判斷EJ是否相等
					if (EJ.equals(t[6].trim())) {
						if (id.indexOf(t[10].trim()) >= 0) {
							System.out.println(line);
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("程式已結束");
		scanner.close();
    }
}
