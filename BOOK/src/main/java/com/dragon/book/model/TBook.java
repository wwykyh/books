package com.dragon.book.model;

import java.util.Date;

public class TBook {
    private String id;

    private String isbn;

    private String sm;

    private String pubId;

    private Date cbrq;

    private String zz;

    private Integer typeId;

    private String tsdl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm == null ? null : sm.trim();
    }

    public String getPubId() {
        return pubId;
    }

    public void setPubId(String pubId) {
        this.pubId = pubId == null ? null : pubId.trim();
    }

    public Date getCbrq() {
        return cbrq;
    }

    public void setCbrq(Date cbrq) {
        this.cbrq = cbrq;
    }

    public String getZz() {
        return zz;
    }

    public void setZz(String zz) {
        this.zz = zz == null ? null : zz.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTsdl() {
        return tsdl;
    }

    public void setTsdl(String tsdl) {
        this.tsdl = tsdl == null ? null : tsdl.trim();
    }
}