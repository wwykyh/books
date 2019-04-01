package com.dragon.book.mail;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 发送邮件
 */

public class DoSendEmail {

    public void sendEmail(String userName,String bookName,String mail){
        // 收件人电子邮箱
        String to = mail;

        // 发件人电子邮箱
        String from = "15159427227@163.com";//部署需要修改

        // 指定发送邮件的主机为 localhost
        String host = "smtp.163.com";//部署需要修改

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);

        properties.put("mail.smtp.auth", "true");
        // 获取默认session对象
        //qq邮箱还需要对SSL进行设置

        Session session = Session.getDefaultInstance(properties,new Authenticator(){
            public PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("15159427227@163.com", "wangnan666"); //发件人邮件用户名、授权码，部署需要修改
            }
        });

        try{
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: 头部头字段
            message.setSubject("图书超时提醒");

            // 设置消息体
            message.setText(userName+"你好！"+"《"+bookName+"》借阅时间超出！请尽快归还！");

            // 发送消息
            Transport.send(message);
            System.out.println("Sent message successfully....from runoob.com");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

}
