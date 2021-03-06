package com.dragon.book.service.mail.mailFactory.impl;

import com.dragon.book.model.MailParameter;
import com.dragon.book.model.TBook;
import com.dragon.book.model.TSysUser;
import com.dragon.book.service.mail.mailFactory.IEmailTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * 超时
 */
public class OvertimeImpl implements IEmailTemplate {
    public String mailName = "超时邮件";
    public String url = "https://www.baidu.com/";
    public String template = "overtimeMailTemplate.ftl";

    public MailParameter createParameter(String fromMail,  String toMail, String userName, String bookName){
        List<TBook> books = new ArrayList<TBook>();
        List<String> bookNameList = new ArrayList<>();
        bookNameList.add(bookName);
        List<String> toMailList = new ArrayList<>();
        toMailList.add(toMail);
        return new MailParameter(fromMail,userName,bookNameList,books,toMailList,mailName,url,template);
    }

    public MailParameter createParameter(String fromMail, List<TBook> books, List<TSysUser> users){
        return new MailParameter();
    }
}
