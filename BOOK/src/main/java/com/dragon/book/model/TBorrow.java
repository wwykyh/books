package com.dragon.book.model;

import java.util.Date;

public class TBorrow {
    private Integer id;

    private String bookId;

    private String sm;

    private String userId;

    private String lxfs;

    private Date jyrq;

    private Date jhghrq;

    private Date xjrq;

    private Date ghrq;

    private Integer status;

    private Integer jyzt;

    private String bz;

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

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm == null ? null : sm.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getLxfs() {
        return lxfs;
    }

    public void setLxfs(String lxfs) {
        this.lxfs = lxfs == null ? null : lxfs.trim();
    }

    public Date getJyrq() {
        return jyrq;
    }

    public void setJyrq(Date jyrq) {
        this.jyrq = jyrq;
    }

    public Date getJhghrq() {
        return jhghrq;
    }

    public void setJhghrq(Date jhghrq) {
        this.jhghrq = jhghrq;
    }

    public Date getXjrq() {
        return xjrq;
    }

    public void setXjrq(Date xjrq) {
        this.xjrq = xjrq;
    }

    public Date getGhrq() {
        return ghrq;
    }

    public void setGhrq(Date ghrq) {
        this.ghrq = ghrq;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getJyzt() {
        return jyzt;
    }

    public void setJyzt(Integer jyzt) {
        this.jyzt = jyzt;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }
}