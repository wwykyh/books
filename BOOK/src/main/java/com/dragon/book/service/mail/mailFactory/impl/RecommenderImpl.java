package com.dragon.book.service.mail.mailFactory.impl;

import com.dragon.book.model.MailParameter;
import com.dragon.book.model.TBook;
import com.dragon.book.model.TSysUser;
import com.dragon.book.service.mail.mailFactory.IEmailTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * 推荐
 */
public class RecommenderImpl implements IEmailTemplate {

    public String mailName = "推荐邮件";
    public String url = "https://www.baidu.com/";
    public String template = "recommenderMailTemplate.ftl";

    public MailParameter createParameter(String fromMail, List<TBook> books, List<TSysUser> users){

        List<String> bookNameList = new ArrayList<>();

        String userName = null;
        List<String> toMailList = new ArrayList<>();
        for(TSysUser user:users){
            toMailList.add(user.getEmail());
        }
        return new MailParameter(fromMail,userName,bookNameList,books,toMailList,mailName,url,template);
    }

    public MailParameter createParameter(String fromMail, String to, String userName, String bookName){
        return  new MailParameter();
    }
}
