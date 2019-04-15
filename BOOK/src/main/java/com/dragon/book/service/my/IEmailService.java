package com.dragon.book.service.my;

public interface IEmailService {

    /**
     * 发送超时提醒邮件
     */
    public void sendOvetimeMail();

    /**
     * 发送推荐邮件
     */
    public void sendRecommenderMail();

    /**
     * 发送提醒邮件
     */
    public void sendReminderMail();

}
