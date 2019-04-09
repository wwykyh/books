package com.dragon.book.service.mail.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * 为JavaMailSender注入参数
 */

@Configuration
public class MailConfiguration {

    @Value(value = "${spring.mail.username}")
    String fromMail;
    @Value(value = "${spring.mail.host}")
    String host;
    @Value(value = "${spring.mail.password}")
    String mailPwd;
    @Value(value = "${spring.mail.port}")
    int port;

    @Bean
    public JavaMailSenderImpl JavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setUsername(fromMail);
        mailSender.setHost(host);
        mailSender.setPassword(mailPwd);
        mailSender.setPort(port);
        mailSender.setDefaultEncoding("UTF-8");
        return mailSender;
    }


}
