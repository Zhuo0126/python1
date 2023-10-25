package com.springboot.demo;

import com.springboot.demo.base.base;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class BankApplication {

    public static void main(String[] args) {
        String pdfFilePath = "D:/TXTT/PDF/test.pdf";
        String password = "J221694941"; // 设置PDF文件的密码
        String txt = "";

        try {
            PDDocument document = PDDocument.load(new File(pdfFilePath), password);
            if (document.isEncrypted()) {
                PDDocumentInformation info = document.getDocumentInformation();
                PDDocumentCatalog catalog = document.getDocumentCatalog();
                PDFTextStripper textStripper = new PDFTextStripper();

//				String contents =textStripper.getText(document);

//				System.out.println(contents);
                for (int pageNum = 0; pageNum < document.getNumberOfPages(); pageNum++) {
                    PDPage page = document.getPage(pageNum);
                    textStripper.setStartPage(pageNum + 1);
                    textStripper.setEndPage(pageNum + 1);
                    String pageText = textStripper.getText(document);
                    txt = txt + pageText;
                }
            } else {
                System.err.println("PDF文件未加密或密码错误。");
            }
            System.out.println(txt);

            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

