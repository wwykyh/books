package com.dragon.book.model;

public class TEbookDown {
    private Integer id;

    private String ebookId;

    private Integer userId;

    private String xzsj;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEbookId() {
        return ebookId;
    }

    public void setEbookId(String ebookId) {
        this.ebookId = ebookId == null ? null : ebookId.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getXzsj() {
        return xzsj;
    }

    public void setXzsj(String xzsj) {
        this.xzsj = xzsj == null ? null : xzsj.trim();
    }
}