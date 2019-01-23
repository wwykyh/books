package com.dragon.book.model;

import java.util.Date;

public class TBook {
    private String bookId;

    private String sm;

    private String cbsmc;

    private Date cbrq;

    private String zz;

    private Date rkrq;

    private Integer status;

    private Integer cs;

    private Integer kc;

    private Integer userId;

    private Integer sh;

    private String tszl;

    private String wz;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId == null ? null : bookId.trim();
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm == null ? null : sm.trim();
    }

    public String getCbsmc() {
        return cbsmc;
    }

    public void setCbsmc(String cbsmc) {
        this.cbsmc = cbsmc == null ? null : cbsmc.trim();
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

    public Date getRkrq() {
        return rkrq;
    }

    public void setRkrq(Date rkrq) {
        this.rkrq = rkrq;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCs() {
        return cs;
    }

    public void setCs(Integer cs) {
        this.cs = cs;
    }

    public Integer getKc() {
        return kc;
    }

    public void setKc(Integer kc) {
        this.kc = kc;
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

    public String getTszl() {
        return tszl;
    }

    public void setTszl(String tszl) {
        this.tszl = tszl == null ? null : tszl.trim();
    }

    public String getWz() {
        return wz;
    }

    public void setWz(String wz) {
        this.wz = wz == null ? null : wz.trim();
    }
}