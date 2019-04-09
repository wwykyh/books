package com.dragon.book.model;

import java.util.List;

/**
 * 邮件工厂返回参数
 */
public class MailParameter {

    private String fromMail;

    private String userName;

    private List<String> bookNameList;

    private List<TBook> books;

    private List<String> toMailList;

    private String mailName;

    private String url;

    private String template;

    public MailParameter() {
    }

    public MailParameter(String fromMail, String userName, List<String> bookNameList, List<TBook> books, List<String> toMailList, String mailName, String url, String template) {
        this.fromMail = fromMail;
        this.userName = userName;
        this.bookNameList = bookNameList;
        this.books = books;
        this.toMailList = toMailList;
        this.mailName = mailName;
        this.url = url;
        this.template = template;
    }

    public List<TBook> getBooks() {
        return books;
    }

    public void setBooks(List<TBook> books) {
        this.books = books;
    }

    public String getFromMail() {
        return fromMail;
    }

    public void setFromMail(String fromMail) {
        this.fromMail = fromMail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getBookNameList() {
        return bookNameList;
    }

    public void setBookNameList(List<String> bookNameList) {
        this.bookNameList = bookNameList;
    }

    public List<String> getToMailList() {
        return toMailList;
    }

    public void setToMailList(List<String> toMailList) {
        this.toMailList = toMailList;
    }

    public String getMailName() {
        return mailName;
    }

    public void setMailName(String mailName) {
        this.mailName = mailName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
