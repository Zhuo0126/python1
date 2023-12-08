package com.springboot.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Scanner;

@SpringBootApplication
public class Application2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		ArrayList<Integer> array = new ArrayList<Integer>();

		while(true){
			System.out.print("Integer :(結束輸入999)");

			int num = scanner.nextInt();
			if (num== 999) {
				break;
			}
			array.add(num);
		}

		int zeroarray[] = new int[array.size()];
		int evenarray[] = new int[array.size()];
		int oddarray[] = new int[array.size()];
		int num1 = 0;
		int num2 = 0;
		int num = 0;
		for (int b = 0; b < array.size(); b++) {
//            System.out.print(array[b] + " ");
			if(array.get(b) == 0){
				zeroarray[num1] = array.get(b);
				num1 +=1;
			}
			else if (array.get(b) % 2 == 0) {

				evenarray[num1] = array.get(b);
				num1 +=1;
			} else {

				oddarray[num2] = array.get(b);
				num2 +=1;
			}
		}

		System.out.print("\n" + "偶數個數:" + num1 + "\n" + "基數個數:" + num2);


	}


}