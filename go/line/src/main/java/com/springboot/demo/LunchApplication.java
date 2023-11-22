package com.springboot.demo;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

@SpringBootApplication
public class LunchApplication {
	private static Properties properties = new Properties();


	public static void main(String[] args) throws Exception {
		//參數檔
		InputStream input = new FileInputStream("./lunch.properties");
		InputStreamReader reader = new InputStreamReader(input, "UTF-8");
		properties.load(reader);

		// 取得今天是星期幾
		DayOfWeek today = LocalDate.now().getDayOfWeek();

		String lunchlist ;
		String drinklist ;
		if(today == DayOfWeek.MONDAY){
			lunchlist = properties.getProperty("eatlist1");
			drinklist = properties.getProperty("drinklist1");
		}else if(today == DayOfWeek.TUESDAY){
			lunchlist = properties.getProperty("eatlist2");
			drinklist = properties.getProperty("drinklist2");
		}else if(today == DayOfWeek.WEDNESDAY){
			lunchlist = properties.getProperty("eatlist3");
			drinklist = properties.getProperty("drinklist3");
		}else if(today == DayOfWeek.THURSDAY){
			lunchlist = properties.getProperty("eatlist4");
			drinklist = properties.getProperty("drinklist4");
		}else{
			lunchlist = properties.getProperty("eatlist5");
			drinklist = properties.getProperty("drinklist5");
		}

		String[] eatArray = lunchlist.split(",");
		String[] drinkArray = drinklist.split(",");

		List<String> eatList =  new ArrayList<>(Arrays.asList(eatArray));
		List<String> waterList = new ArrayList<>(Arrays.asList(drinkArray));

		Random random = new Random();
		int randomEatIndex = random.nextInt(eatList.size());
		int randomDrinkIndex = random.nextInt(waterList.size());

		String randomEatElement = eatList.get(randomEatIndex);
		String randomDrinkElement = waterList.get(randomDrinkIndex);

		System.out.println(randomEatElement);
		System.out.println(randomDrinkElement);

		if(today == DayOfWeek.MONDAY){
			// 移除已選的元素
			eatList.remove(randomEatElement);
			waterList.remove(randomDrinkElement);

			write("eatlist2","drinklist2",eatList,waterList);
		}else if(today == DayOfWeek.TUESDAY){
			// 移除已選的元素
			eatList.remove(randomEatElement);
			waterList.remove(randomDrinkElement);

			write("eatlist3","drinklist3",eatList,waterList);
		}else if(today == DayOfWeek.WEDNESDAY){
			// 移除已選的元素
			eatList.remove(randomEatElement);
			waterList.remove(randomDrinkElement);

			write("eatlist4","drinklist4",eatList,waterList);
		}else if(today == DayOfWeek.THURSDAY){
			// 移除已選的元素
			eatList.remove(randomEatElement);
			waterList.remove(randomDrinkElement);

			write("eatlist5","drinklist5",eatList,waterList);
		}else{
			clearLists("eatlist2", "drinklist2");
			clearLists("eatlist3", "drinklist3");
			clearLists("eatlist4", "drinklist4");
			clearLists("eatlist5", "drinklist5");
		}
	}
	private static void clearLists(String eatKey, String drinkKey) {
		properties.setProperty(eatKey, "");
		properties.setProperty(drinkKey, "");
		try (Writer writer = new OutputStreamWriter(new FileOutputStream("./lunch.properties"), StandardCharsets.UTF_8)) {
			properties.store(writer, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void write(String eat,String drink,List<String> eatlist,List<String> waterlist) throws Exception {
		properties.setProperty(eat, String.join(",", eatlist));
		properties.setProperty(drink, String.join(",", waterlist));

		try (Writer writer = new OutputStreamWriter(new FileOutputStream("./lunch.properties"), StandardCharsets.UTF_8)) {
			properties.store(writer, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
