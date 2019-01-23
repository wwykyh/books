package com.dragon.book.model;

public class TType {
    private Integer typeId;

    private String lxmc;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getLxmc() {
        return lxmc;
    }

    public void setLxmc(String lxmc) {
        this.lxmc = lxmc == null ? null : lxmc.trim();
    }
}