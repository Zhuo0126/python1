package com.springboot.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Pattern;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;

//@SpringBootApplication
public class MailApplication {

    public static void main(String[] args) throws IOException {
        //參數檔
        Properties properties = new Properties();
        InputStream input = new FileInputStream("./email.properties");
        InputStreamReader reader = new InputStreamReader(input, "UTF-8");
        properties.load(reader);

        String emailPDFPath = properties.getProperty("emailPDFPath");
        Integer day=Integer.valueOf(properties.getProperty("day"));
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        String main = properties.getProperty("main");


        System.out.println("成功取得參數");

        // Gmail IMAP 配置
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        props.setProperty("mail.imap.ssl.enable", "true");
        props.setProperty("mail.imap.host", "imap.gmail.com");
        props.setProperty("mail.imap.port", "993");
        try {
            // IMAP連接到Gmail信箱
            Session session = Session.getInstance(props, null);
            Store store = session.getStore();
            store.connect("imap.gmail.com", username, password);

            // 取信箱
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            // 計算日期
            LocalDate oneWeekAgo = LocalDate.now().minusDays(day);
            Date oneWeekAgoDate = Date.from(oneWeekAgo.atStartOfDay(ZoneId.systemDefault()).toInstant());

            // 搜尋日期後的所有信
            SearchTerm searchTerm = new ReceivedDateTerm(ComparisonTerm.GT, oneWeekAgoDate);
            Message[] messages = inbox.search(searchTerm);
            Pattern p1;

            for (Message message : messages) {
                if (message.getSubject().equals(main)) {
                    if (message.getContent() instanceof MimeMultipart) {
                        Multipart multipart = (Multipart) message.getContent();

                        for (int i = 0; i < multipart.getCount(); i++) {
                            BodyPart bodyPart = multipart.getBodyPart(i);

                            if (bodyPart.getDisposition() != null && bodyPart.getDisposition().equalsIgnoreCase(Part.ATTACHMENT)) {
                                if (bodyPart instanceof MimeBodyPart) {
                                    MimeBodyPart part = (MimeBodyPart) bodyPart;
                                    String fileName = part.getFileName();
                                    p1 = Pattern.compile(".*DataDetail.*");

                                    if (fileName != null && fileName.toLowerCase().endsWith(".pdf") && p1.matcher(fileName).find()) {
                                        // pdf放置路徑
                                        LocalDate currentDate = LocalDate.now();
                                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMM");
                                        String savePath = emailPDFPath + currentDate.format(formatter)+"富邦帳單.pdf";

                                        // 保存附件到指定路径
                                        saveAttachment(part, savePath);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            inbox.close(false);
            store.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void saveAttachment(Part part, String destFilePath) throws IOException, MessagingException {
        InputStream is = part.getInputStream();
        FileOutputStream fos = new FileOutputStream(destFilePath);
        byte[] buf = new byte[4096];
        int bytesRead;
        while ((bytesRead = is.read(buf)) != -1) {
            fos.write(buf, 0, bytesRead);
        }
        fos.close();
        is.close();
    }

}

