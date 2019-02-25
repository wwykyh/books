package com.dragon.book.model;

/**
 * 用户返回到前台的电子书的数据模型类
 */
public class TEBookVo extends TEBook{

    private String lxmc;    // 图书类型名称
    private String xm;      // 上传用户名

    public String getLxmc() {
        return lxmc;
    }

    public void setLxmc(String lxmc) {
        this.lxmc = lxmc;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }
}
