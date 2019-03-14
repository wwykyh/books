package com.dragon.book.model;

public class TEbookType {
    private Integer ebookTypeid;

    private String ebookTypemc;

    public Integer getEbookTypeid() {
        return ebookTypeid;
    }

    public void setEbookTypeid(Integer ebookTypeid) {
        this.ebookTypeid = ebookTypeid;
    }

    public String getEbookTypemc() {
        return ebookTypemc;
    }

    public void setEbookTypemc(String ebookTypemc) {
        this.ebookTypemc = ebookTypemc == null ? null : ebookTypemc.trim();
    }
}