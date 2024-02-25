package com.springboot.demo;


import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;


//@SpringBootApplication
//@EnableJms
public class PictureApplication {

	public static void main(String[] args) {
		File imageFile = new File("D:/Picture/test1.jpg");
		ITesseract instance = new Tesseract();  // JNA Interface Mapping
		// ITesseract instance = new Tesseract1(); // JNA Direct Mapping

		try {
			instance.setLanguage("chi_tra");
			instance.setDatapath("C:/Program Files/Tesseract-OCR/tessdata");  // path to tessdata directory

			String result = instance.doOCR(imageFile);
			System.out.println(result);
		} catch (TesseractException e) {
			System.err.println(e.getMessage());
		}
	}
}
