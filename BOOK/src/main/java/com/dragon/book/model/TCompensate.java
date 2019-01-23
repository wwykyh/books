package com.dragon.book.model;

public class TCompensate {
    private Integer id;

    private String bookId;

    private Integer userId;

    private Integer sh;

    private String pc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getSh() {
        return sh;
    }

    public void setSh(Integer sh) {
        this.sh = sh;
    }

    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc == null ? null : pc.trim();
    }
}