package com.dragon.book.pojo;

import com.dragon.book.model.TBorrow;

/**
 * author:lanwq
 * date:2019/03/08
 * description:用来显示数据到图书借阅、归还确认界面
 */
public class TBorrowInfo extends TBorrow {

    private String isbn;            // 图书编号
    private String xm;              // 借阅者姓名
    private String tsdl;            // 图书大类
    private Integer typeId;         // 图书类型Id
    private String lxmc;            //类型名称

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTsdl() {
        return tsdl;
    }

    public void setTsdl(String tsdl) {
        this.tsdl = tsdl;
    }

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
        this.lxmc = lxmc;
    }
}
