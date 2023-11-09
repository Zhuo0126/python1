package com.springboot.demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class CrawlerApplication {

    public static void main(String[] args) {
        String url = "https://docs.google.com/spreadsheets/d/1Xv7Yp00z7kLekujOfioWDe8vaRyei2iJPgaYMOah_bE/edit?pli=1#gid=0";
        EdgeOptions edgeOptions = new EdgeOptions();
        // 允许所有请求（允许浏览器通过远程服务器访问不同源的网页，即跨域访问）
        edgeOptions.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.edge.driver", "/TXTT/driver/msedgedriver.exe");

        EdgeDriver edgeDriver = new EdgeDriver(edgeOptions);
        // 启动需要打开的网页
        edgeDriver.get(url);
        edgeDriver.quit();

//			// 连接到指定的 URL 并获取页面内容
//			Document doc = Jsoup.connect(url).get();
//
//			// 取得所有
//			String body = doc.body().text();
//
//			Elements divElements = doc.select("div"); // 選擇所有的<div>元素
//
//			for (Element div : divElements) {
//				// 取得每個<div>元素的屬性
//				String id = div.attr("id");
//				String style = div.attr("style");
//
//				// 印出<div>元素的資料
//				System.out.println("ID: " + id);
//				System.out.println("Style: " + style);
//			}

    }
}
