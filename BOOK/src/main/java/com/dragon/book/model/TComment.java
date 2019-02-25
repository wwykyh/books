package com.dragon.book.model;

public class TComment {
    private Integer commentId;

    private String isbn;

    private Integer userId;

    private String pjrq;

    private String nr;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPjrq() {
        return pjrq;
    }

    public void setPjrq(String pjrq) {
        this.pjrq = pjrq == null ? null : pjrq.trim();
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr == null ? null : nr.trim();
    }
}