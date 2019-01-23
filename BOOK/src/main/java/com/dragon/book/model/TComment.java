package com.dragon.book.model;

import java.util.Date;

public class TComment {
    private Integer commentId;

    private String bookId;

    private Integer userId;

    private Date pjrq;

    private String nr;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId == null ? null : bookId.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getPjrq() {
        return pjrq;
    }

    public void setPjrq(Date pjrq) {
        this.pjrq = pjrq;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr == null ? null : nr.trim();
    }
}