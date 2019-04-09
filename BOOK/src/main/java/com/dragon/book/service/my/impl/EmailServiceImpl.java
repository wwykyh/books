package com.dragon.book.service.my.impl;

import com.dragon.book.mapper.my.MailSendDao;
import com.dragon.book.model.MailParameter;
import com.dragon.book.model.TBook;
import com.dragon.book.model.TSysUser;
import com.dragon.book.pojo.Borrow;
import com.dragon.book.pojo.UserBorrow;
import com.dragon.book.service.mail.DoSendCCEmail;
import com.dragon.book.service.mail.DoSendEmail;
import com.dragon.book.service.mail.mailFactory.EmailFactory;
import com.dragon.book.service.mail.mailFactory.IEmailTemplate;
import com.dragon.book.service.my.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.List;


@Service
public class EmailServiceImpl implements IEmailService {


    @Value(value = "${spring.mail.username}")
    String fromMail;
    @Value(value = "${templatePath}")
    String templatePath;
    @Autowired
    private MailSendDao mailSendDao;
    @Autowired
    private JavaMailSender sender;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    /**
     * 发送超时邮件
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void sendOvetimeMail() {
        DoSendEmail doSendEmail = new DoSendEmail();
        EmailFactory emailFactory = new EmailFactory();
        try {
            IEmailTemplate overtimeTemplate = emailFactory.createMail("overtimeMail");

            List<UserBorrow> userBorrowList = mailSendDao.findOvertimeUser();

            if (!CollectionUtils.isEmpty(userBorrowList)) {
                for (UserBorrow userBorrow : userBorrowList) {
                  MailParameter mailParameter =  overtimeTemplate.createParameter(fromMail,userBorrow.gettSysUser().getEmail(),userBorrow.gettSysUser().getXm(),userBorrow.getSm());
                  doSendEmail.sendEmail(sender,mailParameter,templatePath);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 发送推荐邮件
     */
    @Scheduled(cron = "0 0/1 11 * * ?")
    public void sendRecommenderMail() {
        DoSendCCEmail doSendCCEmail = new DoSendCCEmail();
        EmailFactory emailFactory = new EmailFactory();
        List<TBook> books = mailSendDao.findBook();
        List<TSysUser> users  = mailSendDao.findUser();
        try {
            IEmailTemplate recommenderTemplate = emailFactory.createMail("recommenderMail");
            if(!CollectionUtils.isEmpty(books)&&!CollectionUtils.isEmpty(users)){
                MailParameter mailParameter = recommenderTemplate.createParameter(fromMail,books,users);
                doSendCCEmail.sendEmail(sender,mailParameter,templatePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送提醒邮件
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void sendReminderMail() {
        DoSendEmail doSendEmail = new DoSendEmail();
        EmailFactory emailFactory = new EmailFactory();
        try {
            IEmailTemplate overtimeTemplate = emailFactory.createMail("reminderMail");

            List<UserBorrow> userBorrowList = mailSendDao.findReminderUser();

            if (!CollectionUtils.isEmpty(userBorrowList)) {
                for (UserBorrow userBorrow : userBorrowList) {
                    MailParameter mailParameter =  overtimeTemplate.createParameter(fromMail,userBorrow.gettSysUser().getEmail(),userBorrow.gettSysUser().getXm(),userBorrow.getSm());
                    doSendEmail.sendEmail(sender,mailParameter,templatePath);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
