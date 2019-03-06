package com.dragon.book.model;

public class TCompensate {
    private Integer id;

    private String isbn;

    private Integer userId;

    private Integer sh;

    private String pcdate;

    private Integer sfpc;

    private Integer iscount;

    private String pc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getSh() {
        return sh;
    }

    public void setSh(Integer sh) {
        this.sh = sh;
    }

    public String getPcdate() {
        return pcdate;
    }

    public void setPcdate(String pcdate) {
        this.pcdate = pcdate == null ? null : pcdate.trim();
    }

    public Integer getSfpc() {
        return sfpc;
    }

    public void setSfpc(Integer sfpc) {
        this.sfpc = sfpc;
    }

    public Integer getIscount() {
        return iscount;
    }

    public void setIscount(Integer iscount) {
        this.iscount = iscount;
    }

    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc == null ? null : pc.trim();
    }
}