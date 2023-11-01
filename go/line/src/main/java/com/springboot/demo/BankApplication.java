package com.springboot.demo;

import com.springboot.demo.base.base;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.encryption.StandardDecryptionMaterial;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

@SpringBootApplication
public class BankApplication {
    private static Boolean change =false;


    public static void main(String[] args) throws IOException {
        //參數檔
        Properties properties = new Properties();
        InputStream input = new FileInputStream("./pdf.properties");
        InputStreamReader reader = new InputStreamReader(input, "UTF-8");
//        BufferedReader bufferedReader = new BufferedReader(new FileReader("./pdf.properties"));
        properties.load(reader);

        String pdfFilePath = properties.getProperty("pdfFilePath");
        String saveFile = properties.getProperty("saveFile");
        String password = properties.getProperty("password");
        String xlsxUrl = properties.getProperty("xlsxUrl");
        String xlsxName = properties.getProperty("xlsxName");
        Boolean autoName = Boolean.parseBoolean(properties.getProperty("autoName"));
        System.out.println("成功取得參數");

        String txt = "";
        List<String> list=null;

        try {
            //解密pdf後另存為不須密碼的pdf
            PDDocument document = PDDocument.load(new File(pdfFilePath), password);
            document.setAllSecurityToBeRemoved(true);
            document.save(saveFile);
            System.out.println("成功備份且解密");

            //讀取pdf並做資料整理
            txt = readRectangle(saveFile);
            System.out.println("成功讀取pdf");

            //逐行讀取
            list = List(txt);
            System.out.println("成功取得所需資料");

            //處理資料
            sortdata(list,xlsxUrl,xlsxName,autoName);
            System.out.println("成功產生xlsx");

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void sortdata(List<String> list,String xlsxUrl,String xlsxName,Boolean autoName) throws IOException {
        InnerClass inner =new InnerClass();
        int rollNum = 0;
        String rt ="";
        if(autoName){
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMM");
            rt=xlsxUrl+currentDate.format(formatter)+"_Bill.xlsx";
        }else{
            rt =xlsxUrl+xlsxName;
        }
        FileOutputStream fileOutputStream = new FileOutputStream(rt);
        Workbook workbook =new XSSFWorkbook();
        Sheet sheet =workbook.createSheet("Bank");
        Row headerRow =sheet.createRow(rollNum);
        //檔案標題
        String[] headerTitles = {"帳務日期","關聯帳戶","類別","使用金流去向","金額","累計","","附註","月份"};
        CellStyle cellStyle =workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setWrapText(true);

        for(int x=0;x<headerTitles.length;x++){
            Cell cell =headerRow.createCell(x);
            cell.setCellValue(headerTitles[x]);
            cell.setCellStyle(cellStyle);
        }

        if(list != null){
            rollNum = rollNum+=1;
            writeXlsx(inner,cellStyle,workbook,list,fileOutputStream,rollNum,sheet);
        }
    }

    public static void writeXlsx(InnerClass inner,CellStyle cellStyle,Workbook workbook,List<String> list,FileOutputStream fileOutputStream,int rowNum,Sheet sheet) throws IOException {
        int i = 0;

        for(int j=0;j<list.size();j+=3){
            inner = new InnerClass();
            Row row =sheet.createRow(rowNum++);
            inner.first = list.get(i);
            inner.second = list.get(i+1);
            inner.third= list.get(i+2);

            String[] part1 = inner.first.split(" ");
            if (part1.length == 2) {
                inner.first_1 = part1[0];
                inner.first_2 = part1[1];
            }else if(part1.length ==1){
                inner.first_1 = part1[0];
            }

            String[] part2 = inner.second.split(" ");
            if (part2.length == 5) {
                inner.second_1=part2[0];
                inner.second_2=part2[1];
                inner.second_3=part2[2];
                inner.second_4=part2[3];
                inner.second_5=part2[4];
            }else if(part2.length ==6){
                inner.second_1=part2[0];
                inner.second_2=part2[1];
                inner.second_3=part2[2];
                inner.second_4=part2[3];
                inner.second_5=part2[4];
                inner.second_6=part2[5];            }
            String[] part3 = inner.third.split(" ");
            if (part3.length == 2) {
                inner.third_1 = part3[0];
                inner.third_2 = part3[1];
            }else if(part3.length ==1){
                inner.third_1 = part3[0];
            }

            //收入/費用分類
            String s = "";
            String m = "";
            Double m1 = null;
            if(inner.second_3.contains("\u00A0") && StringUtils.isNotBlank(inner.second_4)){
                s="收入";
                m=inner.second_4;
                m1=Double.parseDouble(m.replace(",",""));
            } else if (inner.second_4.contains("\u00A0") && StringUtils.isNotBlank(inner.second_3)) {
                s="費用";
                m=inner.second_3;
                m1=Double.parseDouble(m.replace(",","")) * (-1);
            }

            //附註
            String remark ="";
            if(StringUtils.isNotBlank(inner.second_6) && !inner.second_6.contains("\u00A0")){
                remark=inner.second_6;
            }else{
                remark=inner.first_2+ inner.third_2;
                if(remark.trim().equals("nullnull")){
                    remark = "";
                }
            }

            //摘要
            String main="";
            Double p;
            Pattern p1;
            Pattern p2;
            Pattern p3;
            Pattern p4;
            Pattern p5;
            if(s.equals("費用")){
                switch (inner.second_2.trim()){
                    case "行動跨轉":
                        p1 = Pattern.compile(".*612030199391.*");
                        p2 = Pattern.compile(".*369532427300.*");
                        p3 = Pattern.compile(".*82120000027248.*");

                        if(p1.matcher(remark).find()){
                            main = "外勞看護費";
                        }else if (p2.matcher(remark).find()) {
                            main = "醫療_李林玉葉";
                        }else if (p3.matcher(remark).find()) {
                            main = "醫療_李林玉葉";
                        }
                        break;
                    case "行動自轉":
                        p1 = Pattern.compile(".*369532427300.*");
                        p2 = Pattern.compile(".*82120000027248.*");

                        if(p1.matcher(remark).find()){
                            main = "醫療_李林玉葉";
                        } else if (p2.matcher(remark).find()) {
                            main = "醫療_李林玉葉";
                        }
                        break;
                    case "電信費":
                        p1 = Pattern.compile(".*22602409.*");
                        p2 = Pattern.compile(".*22655300.*");

                        if(p1.matcher(remark).find()){
                            main = "電話_慶安街";
                        } else if (p2.matcher(remark).find()) {
                            main = "電話_慶安街";
                        }
                        break;
                    case "電費":
                        p1 = Pattern.compile(".*01800474103.*");
                        p2 = Pattern.compile(".*01800474205.*");
                        p3 = Pattern.compile(".*01800474307.*");
                        p4 = Pattern.compile(".*1754744100.*");
                        p5 = Pattern.compile(".*5947304124.*");

                        if(p1.matcher(remark).find()){
                            main = "電_一樓慶安街";
                        } else if (p2.matcher(remark).find()) {
                            main = "電_二樓慶安街";
                        }else if (p3.matcher(remark).find()) {
                            main = "電_三、四樓慶安街";
                        }else if (p4.matcher(remark).find()) {
                            main = "電_十號一樓";
                        }else if (p5.matcher(remark).find()) {
                            main = "電_其他";
                        }
                        break;
                    case "台水水費":
                        p1 = Pattern.compile(".*CH254374002.*");
                        p2 = Pattern.compile(".*CH252269617.*");

                        if(p1.matcher(remark).find()){
                            main = "水_慶安街";
                        } else if (p2.matcher(remark).find()) {
                            main = "水_十號一樓";
                        }
                        break;
                    case "瓦斯費":
                        p1 = Pattern.compile(".*20046124.*");

                        if(p1.matcher(remark).find()){
                            main = "瓦斯_吉市";
                        }
                        break;
                    case "ＣＤ提款":
                        main = "家用現金";
                        break;
                    case "委代扣":
                        p=Double.parseDouble(m.replace(",",""));
                        if(p == 4032){
                            main="電視_吉市(半年-4032)";
                        } else if (p == 1200) {
                            main="網路_吉市(季-1200-1650)";
                        } else if (p == 1500 && !change) {
                            main="網路_慶安街(季-1500)";
                            change =true;
                        } else if (p == 1500 && change) {
                            main="電視_慶安街(季-1500)";
                            change=false;
                        }
                        break;
                }
            }else if(s.equals("收入")){
                switch (inner.second_2.trim()) {
                    case "行動自轉":
                        p1 = Pattern.compile("俊龍.*");
                        p=Double.parseDouble(m.replace(",",""));

                        if (p1.matcher(remark).find()) {
                            DecimalFormat df = new DecimalFormat("#");
                            main = "李俊龍家用("+df.format(p)+")";
                        }
                        break;
                    case "ＣＤ轉收":
                        p1 = Pattern.compile(".*878902");
                        p2 = Pattern.compile(".*295621");
                        p3 = Pattern.compile("謝欣蒂.*");

                        if(p1.matcher(remark).find()){
                            main = "租金_A_十號一樓(8000) ";
                        } else if (p2.matcher(remark).find()) {
                            main = "租金_B_十號一樓(7500)";
                        }else if (p3.matcher(remark).find()) {
                            main = "租金_C_十號一樓(8500)";
                        }else{
                            p=Double.parseDouble(m.replace(",",""));
                            if(p == 15500){
                                main = "租金_26號十樓+83號車位(15500)";
                            }
                        }
                        break;
                    case "利息":
                        main = "存款利息";
                        break;
                }
            }

            //月份
            String month ="";
            if(StringUtils.isNotBlank(inner.second_1)){
                String[] parts = inner.second_1.split("/");
                if (parts.length >= 2) {
                    month = String.valueOf(Integer.valueOf(parts[1]));

                }
            }

            //特殊條件
            p1 = Pattern.compile(".*富邦信用卡.*");
            p2 = Pattern.compile(".*00206269");
            p3 = Pattern.compile(".*00209099");
            p4 = Pattern.compile(".*11578387368.*");
            p5 = Pattern.compile(".*11368001175.*");
            if(p1.matcher(remark).find() || p4.matcher(remark).find()
                    || p5.matcher(remark).find() ){
                main = "家用短期應收帳款";
            }else if(p2.matcher(remark).find()){
                main = "家用醫療帳戶";
            } else if (p3.matcher(remark).find()) {
                main = "家用長期帳戶-李宥樺";
            }

            //特殊金流分類
            if(main.equals("家用醫療帳戶") || main.equals("家用長期帳戶-李宥樺")
                    || main.equals("家用現金")){
                s="內轉";
            } else if (main.equals("家用短期應收帳款")) {
                s="內轉-信用卡";
            }


            String[] body = {inner.second_1,"家用短期帳戶-李俊龍",s,main,String.valueOf(m1),inner.second_5.replace(",",""),"",remark,month};
            cellStyle =workbook.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.LEFT);
            cellStyle.setWrapText(false);
            for(int x=0;x<body.length;x++){
                Cell cell =row.createCell(x);
                if(x==4 || x==5 || x ==8){
                    cell.setCellValue(Double.parseDouble(body[x]));
                }else{
                    cell.setCellValue(body[x]);
                }
                cell.setCellStyle(cellStyle);
            }
            i=i+=3;
        }
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }

    //內轉類別
    private static String setDetails(String s, double m1) {
        if (m1 > 0) {
            s = "內轉";
        } else {
            s = "內轉-信用卡";
        }
        return s;
    }

    public static class InnerClass {
        private String first;
        private String second;
        private String third;
        private String first_1;
        private String first_2;
        private String second_1;
        private String second_2;
        private String second_3;
        private String second_4;
        private String second_5;
        private String second_6;

        private String third_1;
        private String third_2;

    }


    public static List<String> List(String txt) {
        //取得所需字串
        String extractedText = "";
        int startIndex = txt.indexOf("附註");
        int endIndex = txt.indexOf("帳號");
        // 提取所需的文本
        if (startIndex >= 0 && endIndex >= 0) {
            extractedText = txt.substring(startIndex + "附註".length(), endIndex).trim();
            System.out.println(extractedText);
        } else {
            System.out.println("未找到 '附註' 和/或 '帳號'。");
        }

        String[] lines = extractedText.split("\n");

        // 建立一個list来儲存每一行數據
        List<String> statementLines = new ArrayList<>();

        for (String line : lines) {
            statementLines.add(line);
        }
        return statementLines;
    }
    public static String readRectangle(String filePath) throws Exception {
        File file = new File(filePath);
        PDDocument doc = PDDocument.load(file);

        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;
        String allData = "";

        for (int pageNum = 0; pageNum < doc.getNumberOfPages(); pageNum++) {
            PDPage page = doc.getPage(pageNum);
            if(pageNum == 0){
                x=30;
                y=150;
                width = 600;
                height = 1000;
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);
                //取得所需區域
                Rectangle2D rect = new Rectangle(x, y, width, height);
                stripper.addRegion("area", rect);
                stripper.extractRegions(page);
                //獲取text
                String data = stripper.getTextForRegion("area");
                allData = data.trim() + "\r\n";
                System.out.println(data);
            }else{
                x=30;
                y=0;
                width = 600;
                height = 1000;
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);
                //取得所需區域
                Rectangle2D rect = new Rectangle(x, y, width, height);
                stripper.addRegion("area", rect);
                stripper.extractRegions(page);
                //獲取text
                String data = stripper.getTextForRegion("area");
                allData = allData + data.trim() + "\r\n";
                System.out.println(data);
            }
        }
        doc.close();
        return allData;
    }
}

