package com.dragon.book.model;
/**
 *借阅量自定义的实体类
 */

public class TBookAnalyze {
    private  int type_id;
    private String lxmc;
    private int num;

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getLxmc() {
        return lxmc;
    }

    public void setLxmc(String lxmc) {
        this.lxmc = lxmc;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "TBookAnalyze{" +
                "type_id=" + type_id +
                ", lxmc='" + lxmc + '\'' +
                ", num=" + num +
                '}';
    }
}
