package com.dragon.book.model;

public class TSystemConfig {
    private Integer id;

    private Integer penTime;

    private Integer bookNum;

    private Integer bookTime;

    private Integer infractions;

    private Integer adminBooks;

    private Integer adminTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPenTime() {
        return penTime;
    }

    public void setPenTime(Integer penTime) {
        this.penTime = penTime;
    }

    public Integer getBookNum() {
        return bookNum;
    }

    public void setBookNum(Integer bookNum) {
        this.bookNum = bookNum;
    }

    public Integer getBookTime() {
        return bookTime;
    }

    public void setBookTime(Integer bookTime) {
        this.bookTime = bookTime;
    }

    public Integer getInfractions() {
        return infractions;
    }

    public void setInfractions(Integer infractions) {
        this.infractions = infractions;
    }

    public Integer getAdminBooks() {
        return adminBooks;
    }

    public void setAdminBooks(Integer adminBooks) {
        this.adminBooks = adminBooks;
    }

    public Integer getAdminTime() {
        return adminTime;
    }

    public void setAdminTime(Integer adminTime) {
        this.adminTime = adminTime;
    }
}