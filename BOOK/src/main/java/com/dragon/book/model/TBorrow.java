package com.dragon.book.model;

public class TBorrow {
    private Integer id;

    private String sId;

    private String sm;

    private Integer userId;

    private String lxfs;

    private String jyrq;

    private String jhghrq;

    private String xjrq;

    private String ghrq;

    private Integer status;

    private Integer jyzt;

    private String bz;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId == null ? null : sId.trim();
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm == null ? null : sm.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLxfs() {
        return lxfs;
    }

    public void setLxfs(String lxfs) {
        this.lxfs = lxfs == null ? null : lxfs.trim();
    }

    public String getJyrq() {
        return jyrq;
    }

    public void setJyrq(String jyrq) {
        this.jyrq = jyrq == null ? null : jyrq.trim();
    }

    public String getJhghrq() {
        return jhghrq;
    }

    public void setJhghrq(String jhghrq) {
        this.jhghrq = jhghrq == null ? null : jhghrq.trim();
    }

    public String getXjrq() {
        return xjrq;
    }

    public void setXjrq(String xjrq) {
        this.xjrq = xjrq == null ? null : xjrq.trim();
    }

    public String getGhrq() {
        return ghrq;
    }

    public void setGhrq(String ghrq) {
        this.ghrq = ghrq == null ? null : ghrq.trim();
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