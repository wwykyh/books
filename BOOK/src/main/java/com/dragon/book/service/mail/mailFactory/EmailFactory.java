package com.dragon.book.service.mail.mailFactory;


import com.dragon.book.service.mail.mailFactory.impl.OvertimeImpl;
import com.dragon.book.service.mail.mailFactory.impl.RecommenderImpl;
import com.dragon.book.service.mail.mailFactory.impl.ReminderImpl;

public class EmailFactory {


    public IEmailTemplate createMail(String mailName) throws Exception {
        switch (mailName) {
            case "overtimeMail": //超时邮件
                return new OvertimeImpl();
            case "reminderMail"://提醒邮件
                return new ReminderImpl();
            case "recommenderMail"://推荐邮件
                return new RecommenderImpl();
            default:
                throw new Exception("没有这种邮件模板");
        }
    }

}
